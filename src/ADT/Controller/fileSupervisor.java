package ADT.Controller;
import java.io.*;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fileSupervisor {
    Path currentRelativePath = Paths.get("");
    String stringRelativePathGames = currentRelativePath.toAbsolutePath().toString().concat("\\Saved games");

    public fileSupervisor(){
        try {
            Files.createDirectories(Paths.get(stringRelativePathGames));
        } catch (IOException e) {
        }
    }

    public int saveGame(int nivel, int numeroPartida){
        File directory=new File(stringRelativePathGames);
        int fileCount=directory.list().length;
        Path pathDocumentos = null;
        if (numeroPartida == 0){
            pathDocumentos = Paths.get(stringRelativePathGames.concat("\\"+"PartidaNumero"+String.valueOf(fileCount+1)+"_Nivel"+String.valueOf(nivel)));
        }
        else {
            pathDocumentos = Paths.get(stringRelativePathGames.concat("\\"+"PartidaNumero"+String.valueOf(numeroPartida)+"_Nivel"+String.valueOf(nivel)));
        }
        File fileSerializble = new File(pathDocumentos.toString());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileSerializble));
            oos.writeObject(nivel);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileCount+1;
    }

    public int[] loadGame(String fileName){
        String pathDocumento = stringRelativePathGames.concat("\\"+fileName);
        String patternString = "PartidaNumero(.*)_Nivel";
        int numeroPartida = 0;
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(fileName);
        while (matcher.find()){
            numeroPartida = Integer.parseInt(matcher.group(1));
        }
        Object result = null;
        int[] finalResult = new int[2];
        try (FileInputStream fis = new FileInputStream(pathDocumento);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finalResult[0] = (Integer) result;
        finalResult[1] = numeroPartida;
        return finalResult;
    }

    public String[] getArchivos(){
        String[] res = null;
        File directory = new File(stringRelativePathGames);
        String[] contents = directory.list();
        return contents;
    }
}
