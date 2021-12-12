package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;
import pl.edu.pw.ee.Huffman;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

public class Huffman {
    BinaryTree huffmanTree;
    String decompressed = "";
    String compressed = "";

    public int huffman(String sourcePath, String destinationPath, Boolean compress) {
        String text = "";
        int resInt = 0;

        Path filePath = Paths.get(sourcePath);
        try

        {
            text = Files.readString(filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = "";

        if (compress) {
            resInt = Compression(text);
        result = compressed;
        } else {
            resInt = Decompression(text);
            result = decompressed;
        }

        try {
            FileWriter myWriter = new FileWriter(destinationPath);
            myWriter.write(result);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return resInt;

    }

    public void SaveHuffmanTree(String dstPath) {
        if (huffmanTree == null)
            return;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dstPath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(huffmanTree);
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (Exception e) {
            System.out.println("An error occurred.");
        }

    }

    public void LoadHuffmanTree(String srcPath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(srcPath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            huffmanTree = (BinaryTree) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    public BinaryTree GetHuffmanTree(){
        return huffmanTree;

    }

    public void BinaryCompress(String text, String dstPath) {
        if (text.isEmpty())
            return;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dstPath);
            String[] bytesString = text.split("(?<=\\G.{8})");
            String lastElem = bytesString[bytesString.length - 1];
            String finalLastElem = new String(new char[8 - lastElem.length()]).replace('\0', '0') + lastElem;
            bytesString[bytesString.length - 1] = finalLastElem;

            byte[] buf = new byte[bytesString.length];

            int idx = 0;

            for (String word : bytesString) {
                buf[idx++] = (byte) Integer.parseInt(word, 2);
            }

            fileOutputStream.write(buf);
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    public String BinaryDecompress(String srcPath) {

        try {
            byte[] binary = Files.readAllBytes(Paths.get(srcPath));

            String[] binaryStrings = new String[binary.length];
            int idx = 0;
            for (byte b : binary) {
                binaryStrings[idx++] = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }
            return String.join("", binaryStrings);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public int Compression(String text) {

        List<BinaryTree> treeList = new ArrayList<BinaryTree>();

        for (char letter : text.toCharArray()) {
            int freq = CountOccurences(text, letter);
            BinaryTree tree = new BinaryTree(freq, letter);

            if (!treeList.contains(tree)) {
                treeList.add(tree);
            }

        }

        while (treeList.size() > 1) {
            treeList.sort(new RootComparator());
            BinaryTree firstTree = treeList.get(0);
            BinaryTree secondTree = treeList.get(1);

            int sumFreq = firstTree.getRoot().getFreq() + secondTree.getRoot().getFreq();
            BinaryTree concatenatedTree = new BinaryTree(sumFreq, (char) 0);
            concatenatedTree.getRoot().left = firstTree.getRoot();
            concatenatedTree.getRoot().right = secondTree.getRoot();
            treeList.remove(0);
            treeList.remove(0);
            treeList.add(concatenatedTree);
        }

        compressed = "";
        huffmanTree = treeList.get(0);
        for (char letter : text.toCharArray()) {
            compressed += Code(huffmanTree, letter);
        }

        return compressed.length();
    }

    public int Decompression(String binaryText) {
        decompressed = "";
        if (huffmanTree == null)
            return 0;

        Node current = huffmanTree.getRoot();
        for (char c : binaryText.toCharArray()) {
            if (c == '0')
                current = current.left;
            else if (c == '1')
                current = current.right;

            if (current.getKey() != (char) 0) {
                decompressed += current.getKey();
                current = huffmanTree.getRoot();
            }
        }

        return decompressed.length();
    }

    public String Compressed() {
        return compressed;
    }

    public String Decompressed() {
        return decompressed;
    }

    private int CountOccurences(String word, char searchedChar) {
        int count = 0;

        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) == searchedChar) {
                ++count;
            }
        }
        return count;
    }

    private String resultCode;

    private String Code(BinaryTree tree, char mark) {
        resultCode = "";
        Node node = tree.getRoot();
        code(node, "", mark);
        return resultCode;
    }

    public void code(Node root, String initialString, char searchMark) {
        if (root.left == null && root.right == null) {

            if (root.getKey() == searchMark) {
                resultCode = initialString;
            }

            return;
        }
        code(root.left, initialString + "0", searchMark);
        code(root.right, initialString + "1", searchMark);
    }
}
