package pl.cdv.ffr.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class FTPFileWriterImpl implements FTPFileWriter {
    private static final Logger logger = LoggerFactory.getLogger(FTPFileWriterImpl.class);
    private FTPProperties FTPProperties;
    protected FTPClient ftpClient;

    @Autowired
    public FTPFileWriterImpl(@Autowired FTPProperties FTPProperties) {
        this.FTPProperties = FTPProperties;
    }

    @PostConstruct
    public void init() {
        if (this.FTPProperties.isAutoStart()) {
            logger.debug("Autostarting connection to FTP server.");
            this.open();
        }

    }

    public boolean open() {
        this.close();
        logger.debug("Connecting and logging in to FTP server.");
        this.ftpClient = new FTPClient();
        boolean loggedIn = false;

        try {
            this.ftpClient.connect(this.FTPProperties.getServer(), this.FTPProperties.getPort());
            loggedIn = this.ftpClient.login(this.FTPProperties.getUsername(), this.FTPProperties.getPassword());
            if (this.FTPProperties.getKeepAliveTimeout() > 0) {
                this.ftpClient.setControlKeepAliveTimeout((long)this.FTPProperties.getKeepAliveTimeout());
            }
            this.ftpClient.enterLocalPassiveMode();
        } catch (Exception var3) {
            logger.error(var3.getMessage(), var3);
        }

        return loggedIn;
    }

    public void close() {
        if (this.ftpClient != null) {
            try {
                this.ftpClient.logout();
                this.ftpClient.disconnect();
            } catch (Exception var2) {
                logger.error(var2.getMessage(), var2);
            }
        }

    }

    public boolean loadFile(String remotePath, OutputStream outputStream) {
        try {
            logger.debug("Trying to retrieve a file from remote path " + remotePath);
            return this.ftpClient.retrieveFile(remotePath, outputStream);
        } catch (IOException var4) {
            logger.error(var4.getMessage(), var4);
            return false;
        }
    }

    public boolean saveFile(InputStream inputStream, String destPath, boolean append) {
        try {
            logger.debug("Trying to store a file to destination path " + destPath);
            return append ? this.ftpClient.appendFile(destPath, inputStream) : this.ftpClient.storeFile(destPath, inputStream);
        } catch (IOException var5) {
            logger.error(var5.getMessage(), var5);
            return false;
        }
    }

    public boolean saveFile(String sourcePath, String destPath, boolean append) {
        InputStream inputStream = null;

        try {
            inputStream = (new ClassPathResource(sourcePath)).getInputStream();
        } catch (IOException var6) {
            logger.error(var6.getMessage(), var6);
            return false;
        }

        return this.saveFile(inputStream, destPath, append);
    }

    public boolean isConnected() {
        boolean connected = false;
        if (this.ftpClient != null) {
            try {
                connected = this.ftpClient.sendNoOp();
            } catch (Exception var3) {
                logger.error(var3.getMessage(), var3);
            }
        }

        logger.debug("Checking for connection to FTP server. Is connected: " + connected);
        return connected;
    }
}