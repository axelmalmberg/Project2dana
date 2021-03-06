package project2dana.model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import static project2dana.model.FtpUpload.props;

public class FtpDownload {

    String serverAddress = "ftp.deadesign.se";
    String userId = "deadesign.se";
    String password = "dana1234";
    String remoteDirectory = "/dana";
    String localDirectory = System.getProperty("user.dir");

    public boolean startFTP() {

        props = new Properties();

        try {

            props.getProperty(serverAddress);
            props.getProperty(userId);
            props.getProperty(password);
            props.getProperty(remoteDirectory);
            props.getProperty(localDirectory);

            //new ftp client
            FTPClient ftp = new FTPClient();
            //try to connect
            ftp.connect(serverAddress);
            //login to server
            if (!ftp.login(userId, password)) {
                ftp.logout();
                return false;
            }
            int reply = ftp.getReplyCode();
            //FTPReply stores a set of constants for FTP reply codes. 
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return false;
            }

            //enter active mode
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //get system name
            System.out.println("Remote system is " + ftp.getSystemType());
            //change current directory
            ftp.changeWorkingDirectory(remoteDirectory);
            System.out.println("Current directory is " + ftp.printWorkingDirectory());

            //get list of filenames
            FTPFile[] ftpFiles = ftp.listFiles();

            if (ftpFiles != null && ftpFiles.length > 0) {
                //loop thru files
                for (FTPFile file : ftpFiles) {
                    if (!file.isFile()) {
                        continue;
                    }
                    System.out.println("File is " + file.getName());

                    //get output stream
                    FileOutputStream output;

                    output = new FileOutputStream(localDirectory + file.getName());

                    output = new FileOutputStream(localDirectory + File.separator + file.getName());

                    //get the file from the remote system
                    ftp.retrieveFile(file.getName(), output);
                    //close output stream
                    output.close();

                }
            }

            ftp.logout();
            ftp.disconnect();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
