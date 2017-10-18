package com.vadim0plus.filecloudservice.DAO;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

public class FileDAO {
    protected static Logger log = Logger.getLogger(FileDAO.class.getName());

    byte[] readFile(String fileName) {
        log.debug(">>readFile");
        log.debug("input:fileName="+fileName);
        byte[] buf;
        try(FileChannel fChan = (FileChannel)
                Files.newByteChannel(Paths.get(fileName))) {
            long fSize = fChan.size();
            log.debug("File size:"+fSize);
            buf = new byte[(int)fSize];
            MappedByteBuffer mBuf = fChan.map(
                    FileChannel.MapMode.READ_ONLY,0,fSize);
            mBuf.get(buf);
            log.debug("<<readFile, output size:"+ buf.length);
            return buf;
        } catch(InvalidPathException e) {
            log.error("Invalid path:",e);
        } catch(IOException e) {
            log.error("I/O error while writing file:", e);
        }
        return null;
    }

    boolean writeFile(String fileName, byte buf[]) {
        log.debug(">>writeFile, input:fileName="+fileName+",buf_length="+buf.length);
        try(FileChannel fChan = (FileChannel)
                Files.newByteChannel(Paths.get(fileName),
                        StandardOpenOption.WRITE,
                        StandardOpenOption.READ,
                        StandardOpenOption.CREATE)) {
            MappedByteBuffer mBuf = fChan.map(
                    FileChannel.MapMode.READ_WRITE,0,buf.length);
            mBuf.put(buf);
            log.debug("<<writeFile");
            return true;
        } catch(InvalidPathException e) {
            log.error("Invalid path:",e);
        } catch(IOException e) {
            log.error("I/O error while writing file:",e);
        }
        return false;
    }

    boolean copyFile(String fSrc,String fDest) {
        try {
            log.debug(">>copyFile");
            log.debug("input,fSrc ="+fSrc);
            log.debug("input,fDest ="+fDest);
            Path source = Paths.get(fSrc);
            Path dest = Paths.get(fDest);
            log.debug("Copying process");
            Files.copy(source,dest,StandardCopyOption.REPLACE_EXISTING);
            log.debug("<<copyFile");
            return true;
        } catch(InvalidPathException e) {
            log.error("Invalid path:",e);
        } catch(IOException e) {
            log.error("I/O error while copying:",e);
        }
        return false;
    }
}
