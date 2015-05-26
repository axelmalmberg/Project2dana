package project2dana.model;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.commons.net.ftp.FTP;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUpload {

    static Properties props;

    String serverAddress = "ftp.deadesign.se";
    String userId = "deadesign.se";
    String password = "dana1234";
    String remoteDirectory = "/dana";
    String localDirectory = System.getProperty("user.dir");

    public boolean startFTP(String fileToFTP) {

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

            //enter passive mode
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //get system name
            System.out.println("Remote system is " + ftp.getSystemType());
            //change current directory
            ftp.changeWorkingDirectory(remoteDirectory);
            System.out.println("Current directory is " + ftp.printWorkingDirectory());

            //get input stream
            FileInputStream input;
            input = new FileInputStream(localDirectory + "/" + fileToFTP);
            System.out.println(localDirectory + "/" + fileToFTP);

            //store the file in the remote server
            ftp.storeFile(fileToFTP, input);
            //close the stream
            input.close();

            ftp.logout();
            ftp.disconnect();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

}
