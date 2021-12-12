package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class HuffmanTest {

    private Huffman huffman;

    @Before
    public void setUp() {
        huffman = new Huffman();
    }

    @Test
    public void Should_Return_BitSize_When_String_is_Compressed() {
        Integer expectedInt = 19;
        String stringToCompress = "ABBCCCDDDD";
        int compressed = huffman.Compression(stringToCompress);
        assertEquals(Integer.valueOf(expectedInt), Integer.valueOf(compressed));
    }

    @Test
    public void Should_Return_BitSize_When_String_is_Compressed_ClassExample() {
        Integer expectedInt = 18;
        String stringToCompress = "STONOGA";
        int compressed = huffman.Compression(stringToCompress);
        assertEquals(Integer.valueOf(expectedInt), Integer.valueOf(compressed));
    }

    @Test
    public void Should_BeTheSameString_AfterCompressDecompress() {
        String stringToCompress = "ABBCCCDDDD";
        huffman.Compression(stringToCompress);
        huffman.Decompression(huffman.Compressed());
        String afterDecomp = huffman.Decompressed();
        assertTrue(stringToCompress.equals(afterDecomp));
    }

    @Test
    public void Should_BeTheSameString_AfterCompressDecompress_() {
        String stringToCompress = "ABBCCCDDDD";

        String filePathTree = "tree.txt";

        huffman.Compression(stringToCompress);
        huffman.SaveHuffmanTree(filePathTree);
        String compressedString = huffman.Compressed();
        huffman = new Huffman();
        huffman.LoadHuffmanTree(filePathTree);
        huffman.Decompression(compressedString);
        String afterDecomp = huffman.Decompressed();
        assertTrue(stringToCompress.equals(afterDecomp));
        new File(filePathTree).delete();
    }

    @Test
    public void Should_Save_And_Read_BinaryTree() {
        String stringToCompress = "ABBCCCDDDD";

        String filePathTree = "savedTree.txt";

        huffman.Compression(stringToCompress);
        BinaryTree HuffmanTreeBefore = huffman.GetHuffmanTree();
        huffman.SaveHuffmanTree(filePathTree);
        huffman = new Huffman();
        huffman.LoadHuffmanTree(filePathTree);
        BinaryTree HuffmanTreeAfter = huffman.GetHuffmanTree();
        assertEquals(HuffmanTreeBefore, HuffmanTreeAfter);
        new File(filePathTree).delete();
        
    }

    private static boolean isEqual(Path firstFile, Path secondFile) {
        try {
            if (Files.size(firstFile) != Files.size(secondFile)) {
                return false;
            }

            byte[] first = Files.readAllBytes(firstFile);
            byte[] second = Files.readAllBytes(secondFile);
            return Arrays.equals(first, second);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Test
    public void Should_CompressDecompressed_And_Save() {

        String sourcePath = "niemanie_refren.txt";
        String compressedFilePath = "niemanie_refren_compressed.txt";
        String afterCompressionPath = "niemanie_refren_decompressed.txt";
        String filePathTree = "savedTreeTest.txt";

        huffman.huffman(sourcePath, compressedFilePath, true);
        huffman.SaveHuffmanTree(filePathTree);
        huffman = new Huffman();
        huffman.LoadHuffmanTree(filePathTree);
        huffman.huffman(compressedFilePath, afterCompressionPath, false);

        File expectedText = new File("niemanie_refren.txt");
        File decompressedText = new File("niemanie_refren_decompressed.txt");

        boolean equal = isEqual(expectedText.toPath(), decompressedText.toPath());
        assertTrue(equal);

        new File(compressedFilePath).delete();
        new File(afterCompressionPath).delete();
        new File(filePathTree).delete();
        decompressedText.delete();
    }

}
