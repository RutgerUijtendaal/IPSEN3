package nl.dubio.resources;

import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

@Path("/imageupload")
@Produces(MediaType.TEXT_PLAIN)
public class FileUploadResource {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public boolean uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
                               @FormDataParam("filename") String fileName) throws IOException {
        // TODO: uploadFileLocation should come from config.yml
        String uploadedFileLocation = "/var/www/html/.answer-images/" + fileName;
        return writeToFile(uploadedInputStream, uploadedFileLocation);
    }

    private boolean writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
        int read;
        final int BUFFER_LENGTH = 1024;
        final byte[] buffer = new byte[BUFFER_LENGTH];
        try {
            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
