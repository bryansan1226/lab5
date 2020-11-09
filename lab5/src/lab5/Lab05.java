package lab5;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Lab05 {

	public static void main(String[] args)throws Exception {
		BinarySearchTree playlist= new BinarySearchTree();//Creates a binary tree that songs will be added to
		Scanner scanner=new Scanner(new File("regional-us-weekly-latest.csv"));
		scanner.useDelimiter(",");
		scanner.nextLine();
		/*The following loop goes through the .csv file, pulling the song of the name and the name of the artist and temporarily saving them as variables.
		these variables are then added to a song node that gets added to a binary search tree*/
		for (int i=0;i<200;i++) {
			scanner.nextLine();
			scanner.next();
			String name=scanner.next().replaceAll("^\"|\"$", "");
			String artist=scanner.next().replaceAll("^\"|\"$", "");
			playlist.insert(name,artist);						
		}
		PrintStream out=new PrintStream(new FileOutputStream("ArtistsSorted.txt"));//Creates output file
		System.setOut(out);//Sets console output to file output so that the method writes directly to the .txt file
		
		playlist.inOrder();
		
	}

}
class BinarySearchTree{
	//The song class stores a song name and an artist name, along with pointers to a left and right song node
	class Song{
		private String songTitle;
		private String artistName;
		Song left, right;
		public Song(String name,String artist) {
			songTitle=name;
			artistName=artist;
			left=right=null;
		}
	}
	Song root;
	BinarySearchTree(){
		root=null;
	}
	//Insert calls on doInsert in order to recursively find where the new node fits into the tree.
	void insert(String name, String artist) {
		root=doInsert(root, name,artist);
	}
	Song doInsert(Song root, String name,String artist) {
		if (root==null) {
			root=new Song(name,artist);//If the tree is empty, the node will become the root of the tree. 
			return root;
		}
		
		if(name.charAt(0)<root.songTitle.charAt(0))
			root.left=doInsert(root.left, name,artist);
		else if (name.charAt(0)>root.songTitle.charAt(0))
			root.right=doInsert(root.right, name,artist);
		return root;  //doOrder goes down the tree, comparing itself to the current node, if the name is "lesser" it will place it to the left of the node, and if "greater it'll place it to the right. 
		
	}
	void inOrder() {
		doInOrder(root);
	}
	//doInOrder recursively prints the contents of the song node it's visiting. It will print anything to the left of the tree, then print its own contents, then the contents of the node to the right. 
	void doInOrder(Song root) {
		if (root!=null) {
			doInOrder(root.left);
			System.out.println("Song name: "+root.songTitle);
			System.out.println("Artist: "+root.artistName);
			System.out.println();
			doInOrder(root.right);
		}
	}
}