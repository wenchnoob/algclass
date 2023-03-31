package datastructures;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class that incodes and decodes information using huffman encoding.
 * Reads in a file, whose path is given by the user, and encodes it.
 * It then writes the encoded text to another file, whose path is given by the user.
 * Finally, it takes in the path of another file, reads in that file, decodes it,
 * and prints the output to the console.
 *
 * @author wenchy dutreuil
 * @since 02/28/2023
 *  */
public class HuffmanEncodeDecode {


    /** Main logic for user to interact with system*/
    public static void main(String[] args) {
        System.out.println(new File(".").getAbsolutePath());
        char[] chars = readFile("encode");
        Map<Character, Integer> frequencies = toFrequencies(chars);
        MinHeap<HuffTree<Character>> hheap = heapify(frequencies);
        HuffTree<Character> tree = buildTree(hheap);

        StringBuilder base = new StringBuilder("");
        Map<Character, String> encodings = new HashMap<>();
        makeEncodings(tree.root(), base, encodings);

        String encodedMessage = encode(chars, encodings);
        System.out.println(encodings);
        writeFile(encodedMessage.toString());

        String decodeFile = new String(readFile("decode"));
        StringBuilder decoded = new StringBuilder();
        decode(decodeFile,  decoded, tree.root());
        System.out.println(decoded);
    }

    /**
     * Decodes a message given an encoding, as a huffmantree. stores the decoding in a stringbuilder.
     *
     * @param cont - bit string to be decoded.
     * @param decoding - stringbuilder to store the decoded string.
     * @param root - the root of the huffman tree that holds the codings for our characters.
     * */
    public static void decode(String cont, StringBuilder decoding, HuffBaseNode<Character> root) {
        int i = 0;
        HuffBaseNode<Character> curNode = root;
        while (i < cont.length()) {
            if (curNode.isLeaf()) {
                HuffLeafNode<Character> node = (HuffLeafNode<Character>) curNode;
                decoding.append(node.element());
                curNode = root;
            } else {
                HuffInternalNode<Character> node = (HuffInternalNode<Character>) curNode;
                if (cont.charAt(i) == '0') {
                    curNode = node.left();
                } else {
                    // == '1'
                    curNode = node.right();
                }
                i++;
            }
        }
    }

    /**
     * Encodes a message given an encoding, as a map from character to string, or more specifically bit string.
     *
     * @param cont - characters to be encoded.
     * @param encodings - stringbuilder to store the decoded string.
     * */
    public static String encode(char[] cont, Map<Character, String> encodings) {
        StringBuilder encodedMessage = new StringBuilder();
        for(char c: cont) encodedMessage.append(encodings.getOrDefault(c, ""));
        /** @return the bit string encoding the original string*/
        return encodedMessage.toString();
    }

    /**
     * Walks a huffman tree to derive the encoding for each character
     *
     * @param curNode - the current node of the tree that we are looking at
     * @param encoding - a StringBuilder that stores the path we took to arrive at this node,
     *                 and consequently the nodes encoding
     * @param encodings - a map that will store the encodings for each character.
     *                  map is updated when hit the leaf nod in the tree.
     * */
    public static void makeEncodings(HuffBaseNode<Character> curNode, StringBuilder encoding, Map<Character, String> encodings) {
        if (curNode.isLeaf()) {
            HuffLeafNode<Character> node = (HuffLeafNode<Character>) curNode;
            encodings.put(node.element(), encoding.toString());
        } else {
            HuffInternalNode<Character> node = (HuffInternalNode<Character>) curNode;

            StringBuilder left = new StringBuilder(encoding);
            left.append(0);
            makeEncodings(node.left(), left, encodings);

            StringBuilder right = new StringBuilder(encoding);
            right.append(1);
            makeEncodings(node.right(), right, encodings);
        }
    }

    /**
     * Given a mapping for char to frequency converts the mapping to a minheap of hufftrees.
     *
     * @param frequencies - a map between character and the amount of times it occurs
     *                    in the originally parsed text.
     * @return returns a min heap constructed from a set of character frequency nodes.
     * */
    public static MinHeap<HuffTree<Character>> heapify(Map<Character, Integer> frequencies) {
        List<HuffTree<Character>> leafNodes = frequencies
                .entrySet()
                .stream()
                .map(e -> new HuffTree<>(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        return new MinHeap<>(leafNodes);
    }

    /**
     * Counts the frequencies of chars in a char array
     *
     * @param chars - an array of characters
     * @return a mapping from character and a number indicating the amount of occurrences found
     * */
    public static Map<Character, Integer> toFrequencies(char[] chars) {
        Map<Character, Integer> frequencies = new Hashtable<>();
        for (char c: chars) frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        return frequencies;
    }

    /**
     * Reads the content of a file based on filename provided by user
     *
     * @param mode - a string that will be appended to the prompt, so that the users
     *             knows what they should provide as input
     * @return a char array representing the content of the file.
     * */
    public static char[] readFile(String mode) {
        Scanner in = new Scanner(System.in);
        System.out.printf("Input relative path of file to %s: ", mode);
        String path = in.nextLine();
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            char[] chars = new char[bytes.length];
            for (int i = 0; i < bytes.length; i++) chars[i] = (char)bytes[i];
            return chars;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Writes content to a file based on user the location provided by user, overwrites existing files
     *
     * @param content - a string that holds the content to be written to the file.
     * */
    public static void writeFile(String content) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input relative path of file to write to: ");
        String path = in.nextLine();
        try {
            Path path1 = Paths.get("./" + path);
            if (!Files.exists(path1)) Files.createFile(path1);
            else System.out.println("File already exists. Overwriting!");
            Files.write(path1, content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /** Build a Huffman tree from list hufflist */
    static HuffTree<Character> buildTree(MinHeap<HuffTree<Character>> Hheap) {

        HuffTree tmp1, tmp2, tmp3 = null;
        while (Hheap.heapsize() > 1) { // While two items left
            tmp1 = Hheap.removemin();
            tmp2 = Hheap.removemin();
            tmp3 = new HuffTree<Character>(tmp1.root(), tmp2.root(),
                    tmp1.weight() + tmp2.weight());
            Hheap.insert(tmp3);   // Return new tree to heap
        }
        return tmp3;            // Return the tree
    }

    /** Huffman tree node implementation: Base class */
    interface HuffBaseNode<E> {
        public boolean isLeaf();
        public int weight();
    }
    /** Huffman tree node: Leaf class */
    static class HuffLeafNode<E> implements HuffBaseNode<E> {
        private E element;         // Element for this node
        private int weight;        // Weight for this node
        /** Constructor */
        public HuffLeafNode(E el, int wt)
        { element = el; weight = wt; }
        /** @return The element value */
        public E element() { return element; }
        /** @return The weight */
        public int weight() { return weight; }
        /** Return true */
        public boolean isLeaf() { return true; }
    }
    /** Huffman tree node: Internal class */
    static class HuffInternalNode<E> implements HuffBaseNode<E> {
        private int weight;            // Weight (sum of children)
        private HuffBaseNode<E> left;  // Pointer to left child
        private HuffBaseNode<E> right; // Pointer to right child
        /** Constructor */
        public HuffInternalNode(HuffBaseNode<E> l,
                                HuffBaseNode<E> r, int wt)
        { left = l; right = r; weight = wt; }
        /** @return The left child */
        public HuffBaseNode<E> left() { return left; }
        /** @return The right child */
        public HuffBaseNode<E> right() { return right; }
        /** @return The weight */
        public int weight() { return weight; }
        /** Return false */
        public boolean isLeaf() { return false; }
    }

    /** A Huffman coding tree */
    static class HuffTree<E> implements Comparable<HuffTree<E>>{
        private HuffBaseNode<E> root;  // Root of the tree
        /** Constructors */
        public HuffTree(E el, int wt)
        { root = new HuffLeafNode<E>(el, wt); }
        public HuffTree(HuffBaseNode<E> l,
                        HuffBaseNode<E> r, int wt)
        { root = new HuffInternalNode<E>(l, r, wt); }
        public HuffBaseNode<E> root() { return root; }
        public int weight() // Weight of tree is weight of root
        { return root.weight(); }
        public int compareTo(HuffTree<E> that) {
            if (root.weight() < that.weight()) return -1;
            else if (root.weight() == that.weight()) return 0;
            else return 1;
        } }

}
