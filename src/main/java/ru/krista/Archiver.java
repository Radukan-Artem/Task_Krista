package ru.krista;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.*;

public class Archiver 
{
    private final String outputDir;
    private final String archiveName;

    public Archiver(String outputDir, String archiveName) 
	{
        this.outputDir = outputDir;
        this.archiveName = archiveName;
    }

    public void addtoArchive(String data, String entryName) 
	{
        File dir = new File(outputDir);
        if (!dir.exists()) 
		{
            dir.mkdirs();
        }

        Path archivePath = Paths.get(outputDir, archiveName);
        Path tempArchivePath = Paths.get(outputDir, "temp-" + archiveName);

        boolean exists = Files.exists(archivePath);

        try (
            ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tempArchivePath.toFile())))
        ) 
		{
            if (exists) 
			{
                // Читаем существующий архив и копируем его содержимое
                try (ZipInputStream zin = new ZipInputStream(Files.newInputStream(archivePath))) 
				{
                    ZipEntry entry;
                    while ((entry = zin.getNextEntry()) != null) 
					{
                        zout.putNextEntry(new ZipEntry(entry.getName()));
                        copyStream(zin, zout);
                        zout.closeEntry();
                    }
                }
            }

            // Добавляем новую запись
            zout.putNextEntry(new ZipEntry(entryName));
            zout.write(data.getBytes());
            zout.closeEntry();

            // Переименовываем временный архив обратно в основной
            Files.deleteIfExists(archivePath);
            Files.move(tempArchivePath, archivePath);
        } 
		catch (IOException ex) 
		{
            System.err.println("Ошибка при добавлении в архив: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void copyStream(InputStream is, OutputStream os) throws IOException 
	{
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) 
		{
            os.write(buffer, 0, length);
        }
    }
}
