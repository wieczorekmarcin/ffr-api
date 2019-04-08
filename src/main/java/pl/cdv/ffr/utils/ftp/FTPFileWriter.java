package pl.cdv.ffr.utils.ftp;

import java.io.InputStream;
import java.io.OutputStream;

public interface FTPFileWriter {
    boolean open();

    void close();

    boolean loadFile(String var1, OutputStream var2);

    boolean saveFile(InputStream var1, String var2, boolean var3);

    boolean saveFile(String var1, String var2, boolean var3);

    boolean isConnected();
}
