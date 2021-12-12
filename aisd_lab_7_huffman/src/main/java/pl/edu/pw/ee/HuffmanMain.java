package pl.edu.pw.ee;

public class HuffmanMain {

    public static void main(String[] args) {

        Huffman huffman = new Huffman();

        String sourcePath = "niemanie_refren.txt";
        String destinationPath = "niemanie_refren_compressed.txt";

        int bity = 0;
        bity = huffman.huffman(sourcePath, destinationPath, true);
        System.out.println(String.format("Bity po kompresji: %d", bity));
      
        huffman.BinaryCompress(huffman.Compressed(),"niemanie_refren_binary_compressed.bin");
        huffman.SaveHuffmanTree("drzewo.txt");

        String text = huffman.BinaryDecompress("niemanie_refren_binary_compressed.bin");
        huffman.Decompression(text);

        sourcePath = "niemanie_refren_compressed.txt";
        destinationPath = "niemanie_refren_dekompresja.txt";

        huffman.LoadHuffmanTree("aisd_Huffman\\drzewo.txt");
        
        bity = huffman.huffman(sourcePath, destinationPath, false);
        System.out.println(String.format("Liczba znaków po dekompresji: %d", bity));

    }

}
