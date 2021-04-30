package sample;
import java.io.*;
import java.util.Map;
public class FileManager
{
    String line;
    BufferedReader br;
    BufferedWriter bw;
    public String Reader(String file_name)
    {
        String TextBox="";
        try {
            br = new BufferedReader(new FileReader(file_name+".txt"));
            while((line=br.readLine())!=null)
            {
                TextBox+=line+"\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TextBox;
    }
    public void write_code(File output, Map<Character, Integer> freq, String bits)
    {
        try {
            DataOutputStream os = new DataOutputStream(new FileOutputStream(output));
            os.writeInt(freq.size());
            for (Character character: freq.keySet()) {
                os.writeChar(character);
                os.writeInt(freq.get(character));
            }
            int compressedSizeBits = bits.length();
            Controller.BitArray bitArray = new Controller.BitArray(compressedSizeBits);
            for (int i = 0; i < bits.length(); i++) {
                bitArray.set(i, bits.charAt(i) != '0' ? 1 : 0);
            }
            os.writeInt(compressedSizeBits);
            os.write(bitArray.bytes, 0, bitArray.getSizeInBytes());
            os.flush();
            os.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write_decode(String text,String file_name) throws IOException {
        File file=new File("[decode]"+file_name+".txt");
        if(!file.exists())
        {
            file.createNewFile();
        }
        bw=new BufferedWriter(new FileWriter(file));
        bw.write(text);
        bw.close();
    }
}
