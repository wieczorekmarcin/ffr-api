package pl.cdv.ffr.utils.ftp;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.Date;

@Component
public class FTPHelper {

    @Autowired
    FTPProperties ftpProperties;

    public InputStream getDecodedInputStream(String base64, String extension) throws IOException {
        byte[] file = Base64.getDecoder().decode(base64);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(file));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ImageIO.write(image, extension, buffer);

        return new ByteArrayInputStream(buffer.toByteArray());
    }

    public String createAndSaveDecodedFile(InputStream in, String extension, Date now) throws IOException {
        org.apache.commons.vfs2.FileObject root = getBasicVFSPathPassiveIfNeeded(ftpProperties.getServer(), ftpProperties.getUsername(), ftpProperties.getPassword());
        String directoryName = "/images";
        String fileName = getFileName("." + extension, now);

        String fullFilePath = directoryName + "/" + fileName;
        org.apache.commons.vfs2.FileObject fileObject = root.resolveFile(fullFilePath);
        fileObject.createFile();

        OutputStream out = fileObject.getContent().getOutputStream();

        try {
            IOUtils.copy(in, out);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
        return ftpProperties.getBaseURI() + fullFilePath;
    }

    public String getFileName(String userFileName, Date now) {
        String extension = "";
        int i = userFileName.lastIndexOf('.');
        if (i >= 0) {
            extension = userFileName.substring(i + 1);
        }
        return String.valueOf(now.getTime()) + "." + extension;
    }

    public FileObject getBasicVFSPathPassiveIfNeeded(String connectionPath, String username, String password) throws FileSystemException {
        FileSystemOptions opts = createBasicOpts(username, password);
        if (connectionPath.startsWith("ftp://")) {
            FtpFileSystemConfigBuilder.getInstance().setPassiveMode(opts, true);
        }
        FileSystemManager fsManager = VFS.getManager();
        return fsManager.resolveFile(connectionPath, opts);
    }

    public FileSystemOptions createBasicOpts(String username, String password) throws FileSystemException {
        FileSystemOptions opts = new FileSystemOptions();
        if (username != null && password != null) {
            StaticUserAuthenticator auth = new StaticUserAuthenticator(null, username, password);
            DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
        }
        return opts;
    }
}
