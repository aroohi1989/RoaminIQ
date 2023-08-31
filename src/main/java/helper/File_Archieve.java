package helper;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class File_Archieve
{
    public void create_archive(String SourceDir, String DestinationFile) {
        ZipOutputStream zos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(DestinationFile);
            zos = new ZipOutputStream(fos);
            File sourceDirectory = new File(SourceDir);
            add_to_archive(sourceDirectory, sourceDirectory.getName(), zos);
            System.out.println("Archive created successfully.");
            zos.close();
            fos.close();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        }
    }
    public void add_to_archive(File file, String fileName, ZipOutputStream zos)   {
        try{
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    add_to_archive(f, fileName + "/" + f.getName(), zos);
                }
            }
        } else {
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            zos.putNextEntry(new ZipEntry(fileName));
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
            fis.close();
        }}
        catch (Exception e)
        {
            ExceptionHandling.handleException(e);
        }
        }

        public static void deleteFilesFromFolder(String SourceDir)
        {
            File folder = new File(SourceDir);
            File[] files = folder.listFiles();

            if (files != null)
            {
                for (File file : files)
                {
                    if (file.isFile())
                    {
                        if (file.delete())
                        {
                            System.out.println("Deleted file: " + file.getAbsolutePath());
                        } else
                        {
                            System.out.println("Failed to delete file: " + file.getAbsolutePath());
                        }
                    }
                }
            }

    }
}
