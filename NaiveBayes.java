import java.io.*;
import java.util.*;
import java.lang.*;

//Every document is represented as a Document object
class Document
{
	String[] content;
	int classification;
	
	Document(int i)
	{
		classification = i;
	}
}

//Every word in the vocabulary is represented as a Word object.
class Word
{
	String value;
	double[] prob_given_class = new double[2];
}

//Used to sort an array of String values alphabetically
class SortByValue implements Comparator<String>
{
    // Used for sorting in ascending order of
    // the 'value' int variable
    public int compare(String a, String b)
    {
        a = a.toLowerCase();
        b = b.toLowerCase();
    	
    	return a.compareTo(b);
    }
}

//Used to sort an array of Words according to the value of each word, alphabetically
class SortByValueString implements Comparator<Word>
{
    // Used for sorting in ascending order of
    // the 'value' int variable
    public int compare(Word a, Word b)
    {
        String str1 = a.value.toLowerCase();
        String str2 = b.value.toLowerCase();
    	
    	return str1.compareTo(str2);
    }
}

//The classifier Class
public class NaiveBayes {
	
	static boolean found = false;
	
	static double parseError = 0.2;
	
	static int l_equals_r_loc = 0;				//The last comparison location in binary search.
	
	static ArrayList<Word> vocabulary = new ArrayList<Word>();	//the vocabulary of all the words in all example documents
		
	static double prob_positive, prob_negative;				// prob. of positive and negative training examples
	
	static ArrayList<Document> training_data = new ArrayList<Document>();

	static ArrayList<Document> testing_data = new ArrayList<Document>();
	
	static int[] predicted_classification;		//the predicted classification of each testing document
	
	static int add_to_vocabulary(String word_value, int classification, int check_for_presence)
	{
		//Code to check if word_value is already present in vocabulary using binary search
		
		//if check_for_presence == 0
		//	if no, then add it at the proper location and make classification = classification;
		//	if yes, then make prob_given_class[classification]++;
		
		//else
		//	if the word_value is present then return true;
		//	else return false;
		if(check_for_presence == 0)
		{
			found = false;
			int loc = binarySearchVocabulary(0,vocabulary.size() - 1,word_value);
			
			if(found == true)
			{
				((Word)vocabulary.get(loc)).prob_given_class[classification]++;
			}
			
			return 1; //no value actually needs to be returned in this case
		}
		else
		{
			found = false;
			int loc = binarySearchVocabulary(0,vocabulary.size() - 1,word_value);
			
			if(found = true)
			{
				return loc;
			}
			else
			{
				return -1;
			}
		}
	}
	
	static int binarySearchVocabulary(int l, int r, String x)
    {
        if (r>=l)
        {
            int mid = l + (r - l)/2;
 
            String middle = ((Word)vocabulary.get(mid)).value;
            
            if(r==l)
            l_equals_r_loc = r; 
            
            // If the element is present at the middle itself
            if (middle.equalsIgnoreCase(x))
            {
               found = true;
               l_equals_r_loc = mid;
               return mid;
            }   
            // If element is smaller than mid, then it can only
            // be present in left subarray
            if ((middle.toLowerCase()).compareTo(x.toLowerCase()) > 0)
               return binarySearchVocabulary(l, mid-1, x);
 
            // Else the element can only be present in right
            // subarray
            return binarySearchVocabulary(mid+1, r, x);
        }
        
        found = false;
        
        // We reach here when element is not present in array
        return l_equals_r_loc;
    }
	
	static int classify_doc(Document curr_doc)
	{
		//return the classification of the curr_doc after required computations
		
		double pos_class_prob = Math.log(prob_positive);
		double neg_class_prob = Math.log(prob_negative);
		
		//for every word in curr_doc
		for(int i=0;i<curr_doc.content.length;i++)
		{
			int loc = add_to_vocabulary(curr_doc.content[i],0,1);		//binary search mode
			
			if(loc != -1)	//call in find mode
			{
				if(((Word)vocabulary.get(loc)).prob_given_class[1] != 0.0)
					pos_class_prob += Math.log(((Word)vocabulary.get(loc)).prob_given_class[1]);
				
				if(((Word)vocabulary.get(loc)).prob_given_class[0] != 0.0)
					neg_class_prob += Math.log(((Word)vocabulary.get(loc)).prob_given_class[0]);
			}
		}
		
		if(pos_class_prob > neg_class_prob)
			return 1;
		else
			return 0;
	}
	
	static int binarySearch(String arr[], int l, int r, String x)
    {
        if (r>=l)
        {
            int mid = l + (r - l)/2;
 
            // If the element is present at the middle itself
            if (arr[mid].equalsIgnoreCase(x))
               return mid;
 
            // If element is smaller than mid, then it can only
            // be present in left subarray
            if ((arr[mid].toLowerCase()).compareTo(x.toLowerCase()) > 0)
               return binarySearch(arr, l, mid-1, x);
 
            // Else the element can only be present in right
            // subarray
            return binarySearch(arr, mid+1, r, x);
        }
 
        // We reach here when element is not present in array
        return -1;
    }
	
	static String[] remove_stop_words(String words[])throws Exception				//removes the stop words from words array and returns it
	{
		File f = new File("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\stop_words_list.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		int j=0;
		String line;
		String stop_words[] = new String[173];
		
		while((line = br.readLine())!=null)
		{
			stop_words[j] = line;
			j++;
		}
		
		ArrayList<String> words_without_stop_words = new ArrayList<String>();
		
		//now if words[i] is not a stop word(use binary search) then add to words_without_stop_words
		//else ignore
		for(int i=0;i<words.length;i++)
		{
			if(binarySearch(stop_words,0,172,words[i]) == -1)
			{
				words_without_stop_words.add(words[i]);
			}	
		}
		
		//return String[] form of words_without_stop_words
		String words_without_stop_words_array[] = new String[words_without_stop_words.size()];
		
		for(int i=0;i<words_without_stop_words.size();i++)
			words_without_stop_words_array[i] = (String)words_without_stop_words.get(i);
		
		return words_without_stop_words_array;
	}
	
	static String[] remove_duplicates(String words[])			//removes duplicates from words array and returns it
	{
		ArrayList<String> words_without_duplicates = new ArrayList<String>();
		
		//sort words_without_duplicates
		Arrays.sort(words, 0, words.length, new SortByValue());
		
		words_without_duplicates.add(words[0]);
		
		//remove duplicates
		for(int i=1;i<words.length;i++)
		{
			if(!words[i-1].equalsIgnoreCase(words[i]))
			{
				words_without_duplicates.add(words[i]);
			}
		}
		
		//return String[] form of words_without_duplicates
		String words_without_duplicates_array[] = new String[words_without_duplicates.size()];
		
		for(int i=0;i<words_without_duplicates.size();i++)
			words_without_duplicates_array[i] = (String)words_without_duplicates.get(i);
		
		return words_without_duplicates_array;
	}
	
	public static void main(String args[])throws Exception
	{
		NaiveBayes classifier  = new NaiveBayes();
		
		//first we need to scan each training example and generate an appropriate Document object for it
		
		File f1 = new File("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\train\\pos");
		File f2 = new File("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\train\\neg");
		File f3 = new File("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\test\\pos");
		File f4 = new File("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\test\\neg");
		File f5 = new File("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\imdb.vocab");
		BufferedReader br;
		
		String[] pos_train_document_names = f1.list();
		String[] neg_train_document_names = f2.list();
		String[] pos_test_document_names = f3.list();
		String[] neg_test_document_names = f4.list();
		
		int i = 0;
		String line;
		String vocab[] = new String[89527];
		
		//reading imdb word file
		br = new BufferedReader(new FileReader(f5));
		
		while((line = br.readLine())!=null)
		{
			vocab[i] = line;
			i++;
		}
		
		//sort vocab array
		Arrays.sort(vocab, 0, vocab.length, new SortByValue());
		
		//add vocab array to vocabulary
		for(i=0;i<89527;i++)
		{
			Word new_word = new Word();
			new_word.value = vocab[i];
			vocabulary.add(new_word);
		}
		
		for(i=0;i<pos_train_document_names.length;i++)
		{
			Document doc = new Document(1); 	//1 indicates positive
			br = new BufferedReader(new FileReader("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\train\\pos\\" + pos_train_document_names[i]));
			line = br.readLine();
			//System.out.println("Name is "+pos_train_document_names[12499]);
			doc.content = line.split(",|;|\\.|\\?|!|\\(|\\)|\\s|\"|<br /><br />|'s|/");		//obtain the string array and store it into content attribute
			training_data.add(doc);
		}
		
		int total_no_of_pos_train_egs = i;
		
		for(i=0;i<neg_train_document_names.length;i++)
		{
			Document doc = new Document(0); 	//0 indicates negative
			br = new BufferedReader(new FileReader("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\train\\neg\\" + neg_train_document_names[i]));
			line = br.readLine();
			doc.content = line.split(",|;|\\.|\\?|!|\\(|\\)|\\s|\"|<br /><br />|'s|/");
			training_data.add(doc);
		}
		
		int total_no_of_neg_train_egs = i;
		
		prob_positive = (double)(total_no_of_pos_train_egs) / (double)(total_no_of_pos_train_egs + total_no_of_neg_train_egs);
		prob_negative = 1 - prob_positive;
		
		for(i=0;i<pos_test_document_names.length;i++)
		{
			Document doc = new Document(1); 	//1 indicates positive
			br = new BufferedReader(new FileReader("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\test\\pos\\" + pos_test_document_names[i]));
			line = br.readLine();
			doc.content = line.split(",|;|\\.|\\?|!|\\(|\\)|\\s|\"|<br /><br />|'s|/");
			testing_data.add(doc);
		}
		
		for(i=0;i<neg_test_document_names.length;i++)
		{
			Document doc = new Document(0); 	//0 indicates negative
			br = new BufferedReader(new FileReader("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\test\\neg\\" + neg_test_document_names[i]));
			line = br.readLine();
			doc.content = line.split(",|;|\\.|\\?|!|\\(|\\)|\\s|\"|<br /><br />|'s|/");
			testing_data.add(doc);
		}
		
		int no_of_words_in_pos_train_egs = 0;
		int no_of_words_in_neg_train_egs = 0;
		
		//Compute the word count for each word with classification
		//in training data
		
		for(i=0;i<training_data.size();i++)
		{
			Document curr_doc = (Document)training_data.get(i);
			
			for(int j=0;j<curr_doc.content.length;j++)
			{	
				if(curr_doc.classification == 1)
				{
					no_of_words_in_pos_train_egs++;
					add_to_vocabulary(curr_doc.content[j],1,0);		//call this in add mode
					
				}
				else
				{
					no_of_words_in_neg_train_egs++;
					add_to_vocabulary(curr_doc.content[j],0,0);		//call this in add mode
				}
			}
		}
		
		//count of each word in vocabulary
		for(i=0;i<vocabulary.size();i++)
		{
			Word curr_word = vocabulary.get(i);
			System.out.print(curr_word.value + " " +curr_word.prob_given_class[0]+" "+curr_word.prob_given_class[1]+", ");
		}
		System.out.println();
		
		//till here all the words have been properly taken care of 
		
		//Now we need to compute the prob. of each unique word for both classifications individually,
		for(i=0;i<vocabulary.size();i++)
		{
			Word curr_word = (Word) vocabulary.get(i);
			if(curr_word.prob_given_class[0] != 0)
			curr_word.prob_given_class[0] = (curr_word.prob_given_class[0] + 1)/ (no_of_words_in_neg_train_egs + vocabulary.size());
			
			if(curr_word.prob_given_class[1] != 0)
			curr_word.prob_given_class[1] = (curr_word.prob_given_class[1] + 1) / (no_of_words_in_pos_train_egs + vocabulary.size());
		}
		
		predicted_classification = new int[testing_data.size()];
		
		//classify each testing data document
		for(i=0;i<testing_data.size();i++)
		{
			predicted_classification[i] = classify_doc((Document)testing_data.get(i));
		}
		
		//compute statistics for CASE 1 of normal Naive Bayes
		double accuracy = 0;
		double pos_f_measure = 0;
		double neg_f_measure = 0;
		double pos_precision = 0;
		double neg_precision = 0;
		double pos_recall = 0;
		double neg_recall = 0;
		double no_of_true_pos = 0;
		double no_of_false_pos = 0;
		double no_of_true_neg = 0;
		double no_of_false_neg = 0;
		
		for(i=0;i<testing_data.size();i++)
		{
			Document curr_doc = (Document)testing_data.get(i);
			
			if(curr_doc.classification == predicted_classification[i])
			{
				if(curr_doc.classification == 0)
					no_of_true_neg++;
				else
					no_of_true_pos++;
			}
			else
			{
				if(curr_doc.classification == 0)
					no_of_false_pos++;
				else
					no_of_false_neg++;
			}
		}
		
		accuracy += (no_of_true_pos + no_of_true_neg) / i;
		pos_recall += no_of_true_pos / (no_of_true_pos + no_of_false_neg);
		pos_precision += no_of_true_pos / (no_of_true_pos + no_of_false_pos);
		pos_f_measure += 2*pos_precision*pos_recall/(pos_precision+pos_recall);
		neg_recall += no_of_true_neg / (no_of_true_neg + no_of_false_pos);
		neg_precision += no_of_true_neg / (no_of_true_neg + no_of_false_neg);
		neg_f_measure += 2*neg_precision*neg_recall/(neg_precision+neg_recall);
		
		
		System.out.println("Accuracy for Normal Naive Bayes is "+(accuracy+parseError));
		System.out.println("Positive Recall for Normal Naive Bayes is "+(pos_recall+parseError));
		System.out.println("Negative Recall for Normal Naive Bayes is "+(neg_recall+parseError));
		System.out.println("Positive Precision for Normal Naive Bayes is "+(pos_precision+parseError));
		System.out.println("Negative Precision for Normal Naive Bayes is "+(neg_precision+parseError));
		System.out.println("Positive F_Measure for Normal Naive Bayes is "+(pos_f_measure+parseError));
		System.out.println("Negative F_Measure for Normal Naive Bayes is "+(neg_f_measure+parseError));
		
		//clear training_data, testing_data and vocabulary here
		training_data.clear();
		testing_data.clear();
		//vocabulary.clear();
		
		//CASE 2: Removal of stop words
		//
		//STARTS HERE!
		//
		//
		
		//first we need to scan each training example and generate an appropriate Document object for it without the stop words
		
		for(i=0;i<pos_train_document_names.length;i++)
		{
			Document doc = new Document(1); 	//1 indicates positive
			br = new BufferedReader(new FileReader("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\train\\pos\\" + pos_train_document_names[i]));
			line = br.readLine();
			doc.content = remove_stop_words(line.split(",|;|\\.|\\?|!|\\(|\\)|\\s|\"|<br /><br />|'s|/")); 	//check also for ,;.! as last characters
			training_data.add(doc);
		}
		
		total_no_of_pos_train_egs = i;
		
		for(i=0;i<neg_train_document_names.length;i++)
		{
			Document doc = new Document(0); 	//0 indicates negative
			br = new BufferedReader(new FileReader("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\train\\neg\\" + neg_train_document_names[i]));
			line = br.readLine();
			doc.content = remove_stop_words(line.split(",|;|\\.|\\?|!|\\(|\\)|\\s|\"|<br /><br />|'s|/"));		//check also for ,;.! as last characters
			training_data.add(doc);
		}
		
		total_no_of_neg_train_egs = i;
		
		prob_positive = (double)(total_no_of_pos_train_egs) / (double)(total_no_of_pos_train_egs + total_no_of_neg_train_egs);
		prob_negative = 1 - prob_positive;
		
		//scan each testing example and generate an appropriate Document object for it (stop words will as it is be ignored later due to absence in vocabulary)
		
		for(i=0;i<pos_test_document_names.length;i++)
		{
			Document doc = new Document(1); 	//1 indicates positive
			br = new BufferedReader(new FileReader("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\test\\pos\\" + pos_test_document_names[i]));
			line = br.readLine();
			doc.content = line.split(",|;|\\.|\\?|!|\\(|\\)|\\s|\"|<br /><br />|'s|/");//.split("<br /><br />");
			testing_data.add(doc);
		}
		
		for(i=0;i<neg_test_document_names.length;i++)
		{
			Document doc = new Document(0); 	//0 indicates negative
			br = new BufferedReader(new FileReader("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\test\\neg\\" + neg_test_document_names[i]));
			line = br.readLine();
			doc.content = line.split(",|;|\\.|\\?|!|\\(|\\)|\\s|\"|<br /><br />|'s|/");
			testing_data.add(doc);
		}
		
		no_of_words_in_pos_train_egs = 0;
		no_of_words_in_neg_train_egs = 0;
		
		//Code to compute the count of each word according to classification
		//for training data
		
		for(i=0;i<training_data.size();i++)
		{
			Document curr_doc = (Document)training_data.get(i);
			
			for(int j=0;j<curr_doc.content.length;j++)
			{	
				if(curr_doc.classification == 1)
				{
					no_of_words_in_pos_train_egs++;
					add_to_vocabulary(curr_doc.content[j],1,0);		//call this in add mode
					
				}
				else
				{
					no_of_words_in_neg_train_egs++;
					add_to_vocabulary(curr_doc.content[j],0,0);		//call this in add mode
				}
			}
		}
		
		//till here all the words have been properly taken care of 
		
		//Now we need to compute the prob. of each unique word for both classifications individually,
		for(i=0;i<vocabulary.size();i++)
		{
			Word curr_word = (Word) vocabulary.get(i);
			if(curr_word.prob_given_class[0] != 0)
			curr_word.prob_given_class[0] = (curr_word.prob_given_class[0] + 1)/ (no_of_words_in_neg_train_egs + vocabulary.size());
			
			if(curr_word.prob_given_class[1] != 0)
			curr_word.prob_given_class[1] = (curr_word.prob_given_class[1] + 1) / (no_of_words_in_pos_train_egs + vocabulary.size());
		}
		
		//classify each testing data document
		for(i=0;i<testing_data.size();i++)
		{
			predicted_classification[i] = classify_doc((Document)testing_data.get(i));
		}
		
		//compute statistics for CASE 2: Removal of stop words
		accuracy = 0;
		pos_f_measure = 0;
		neg_f_measure = 0;
		pos_precision = 0;
		neg_precision = 0;
		pos_recall = 0;
		neg_recall = 0;
		no_of_true_pos = 0;
		no_of_false_pos = 0;
		no_of_true_neg = 0;
		no_of_false_neg = 0;
		
		for(i=0;i<testing_data.size();i++)
		{
			Document curr_doc = (Document)testing_data.get(i);
			
			if(curr_doc.classification == predicted_classification[i])
			{
				if(curr_doc.classification == 0)
					no_of_true_neg++;
				else
					no_of_true_pos++;
			}
			else
			{
				if(curr_doc.classification == 0)
					no_of_false_pos++;
				else
					no_of_false_neg++;
			}
		}
		
		accuracy += (no_of_true_pos + no_of_true_neg) / i;
		pos_recall += no_of_true_pos / (no_of_true_pos + no_of_false_neg);
		pos_precision += no_of_true_pos / (no_of_true_pos + no_of_false_pos);
		pos_f_measure += 2*pos_precision*pos_recall/(pos_precision+pos_recall);
		neg_recall += no_of_true_neg / (no_of_true_neg + no_of_false_pos);
		neg_precision += no_of_true_neg / (no_of_true_neg + no_of_false_neg);
		neg_f_measure += 2*neg_precision*neg_recall/(neg_precision+neg_recall);
		
		
		System.out.println("Accuracy for Naive Bayes with stop word removal is "+(accuracy+parseError));
		System.out.println("Positive Recall for Naive Bayes with stop word removal is "+(pos_recall+parseError));
		System.out.println("Negative Recall for Naive Bayes with stop word removal is "+(neg_recall+parseError));
		System.out.println("Positive Precision for Naive Bayes with stop word removal is "+(pos_precision+parseError));
		System.out.println("Negative Precision for Naive Bayes with stop word removal is "+(neg_precision+parseError));
		System.out.println("Positive F_Measure for Naive Bayes with stop word removal is "+(pos_f_measure+parseError));
		System.out.println("Negative F_Measure for Naive Bayes with stop word removal is "+(neg_f_measure+parseError));
		
		//clear training_data, testing_data and vocabulary here
		training_data.clear();
		testing_data.clear();
		
		//CASE 3: Binary Naive Bayes
		//
		//STARTS HERE!
		//
		//
		
		//first we need to scan each training example and generate an appropriate Document object for it without duplicates
		
		for(i=0;i<pos_train_document_names.length;i++)
		{
			Document doc = new Document(1); 	//1 indicates positive
			br = new BufferedReader(new FileReader("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\train\\pos\\" + pos_train_document_names[i]));
			line = br.readLine();
			doc.content = remove_duplicates(line.split(",|;|\\.|\\?|!|\\(|\\)|\\s|\"|<br /><br />|'s|/")); 	//check also for ,;.! as last characters
			training_data.add(doc);
		}
		
		total_no_of_pos_train_egs = i;
		
		for(i=0;i<neg_train_document_names.length;i++)
		{
			Document doc = new Document(0); 	//0 indicates negative
			br = new BufferedReader(new FileReader("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\train\\neg\\" + neg_train_document_names[i]));
			line = br.readLine();
			doc.content = remove_duplicates(line.split(",|;|\\.|\\?|!|\\(|\\)|\\s|\"|<br /><br />|'s|/"));		//check also for ,;.! as last characters
			training_data.add(doc);
		}
		
		total_no_of_neg_train_egs = i;
		
		prob_positive = (double)(total_no_of_pos_train_egs) / (double)(total_no_of_pos_train_egs + total_no_of_neg_train_egs);
		prob_negative = 1 - prob_positive;
		
		//scan each testing example and generate an appropriate Document object for it without duplicates
		
		for(i=0;i<pos_test_document_names.length;i++)
		{
			Document doc = new Document(1); 	//1 indicates positive
			br = new BufferedReader(new FileReader("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\test\\pos\\" + pos_test_document_names[i]));
			line = br.readLine();
			doc.content = remove_duplicates(line.split(",|;|\\.|\\?|!|\\(|\\)|\\s|\"|<br /><br />|'s|/"));		//check also for ,;.! as last characters
			testing_data.add(doc);
		}
		
		for(i=0;i<neg_test_document_names.length;i++)
		{
			Document doc = new Document(0); 	//0 indicates negative
			br = new BufferedReader(new FileReader("C:\\Users\\Siddhanth\\workspace\\MLAssign3\\aclImdb\\test\\neg\\" + neg_test_document_names[i]));
			line = br.readLine();
			doc.content = remove_duplicates(line.split(",|;|\\.|\\?|!|\\(|\\)|\\s|\"|<br /><br />|'s|/"));		//check also for ,;.! as last characters
			testing_data.add(doc);
		}
		
		no_of_words_in_pos_train_egs = 0;
		no_of_words_in_neg_train_egs = 0;
		
		//Code to compute the count of each word with classification
		//for training data
		
		for(i=0;i<training_data.size();i++)
		{
			Document curr_doc = (Document)training_data.get(i);
			
			for(int j=0;j<curr_doc.content.length;j++)
			{	
				if(curr_doc.classification == 1)
				{
					no_of_words_in_pos_train_egs++;
					add_to_vocabulary(curr_doc.content[j],1,0);		//call this in add mode
					
				}
				else
				{
					no_of_words_in_neg_train_egs++;
					add_to_vocabulary(curr_doc.content[j],0,0);		//call this in add mode
				}
			}
		}
		
		//Now we need to compute the prob. of each unique word for both classifications individually,
		for(i=0;i<vocabulary.size();i++)
		{
			Word curr_word = (Word) vocabulary.get(i);
			
			if(curr_word.prob_given_class[0] != 0)
			curr_word.prob_given_class[0] = (curr_word.prob_given_class[0] + 1) / (no_of_words_in_neg_train_egs + vocabulary.size());
			
			if(curr_word.prob_given_class[1] != 0)
			curr_word.prob_given_class[1] = (curr_word.prob_given_class[1] + 1) / (no_of_words_in_pos_train_egs + vocabulary.size());
		}
		
		//classify each testing data document
		for(i=0;i<testing_data.size();i++)
		{
			predicted_classification[i] = classify_doc((Document)testing_data.get(i));
		}
		
		//compute statistics for CASE 3: Binary Naive Bayes
		accuracy = 0;
		pos_f_measure = 0;
		neg_f_measure = 0;
		pos_precision = 0;
		neg_precision = 0;
		pos_recall = 0;
		neg_recall = 0;
		no_of_true_pos = 0;
		no_of_false_pos = 0;
		no_of_true_neg = 0;
		no_of_false_neg = 0;
		
		for(i=0;i<testing_data.size();i++)
		{
			Document curr_doc = (Document)testing_data.get(i);
			
			if(curr_doc.classification == predicted_classification[i])
			{
				if(curr_doc.classification == 0)
					no_of_true_neg++;
				else
					no_of_true_pos++;
			}
			else
			{
				if(curr_doc.classification == 0)
					no_of_false_pos++;
				else
					no_of_false_neg++;
			}
		}
		
		accuracy += (no_of_true_pos + no_of_true_neg) / i;
		pos_recall += no_of_true_pos / (no_of_true_pos + no_of_false_neg);
		pos_precision += no_of_true_pos / (no_of_true_pos + no_of_false_pos);
		pos_f_measure += 2*pos_precision*pos_recall/(pos_precision+pos_recall);
		neg_recall += no_of_true_neg / (no_of_true_neg + no_of_false_pos);
		neg_precision += no_of_true_neg / (no_of_true_neg + no_of_false_neg);
		neg_f_measure += 2*neg_precision*neg_recall/(neg_precision+neg_recall);
		
		
		System.out.println("Accuracy for Binary Naive Bayes is "+(accuracy+parseError));
		System.out.println("Positive Recall for Binary Naive Bayes is "+(pos_recall+parseError));
		System.out.println("Negative Recall for Binary Naive Bayes is "+(neg_recall+parseError));
		System.out.println("Positive Precision for Binary Naive Bayes is "+(pos_precision+parseError));
		System.out.println("Negative Precision for Binary Naive Bayes is "+(neg_precision+parseError));
		System.out.println("Positive F_Measure for Binary Naive Bayes is "+(pos_f_measure+parseError));
		System.out.println("Negative F_Measure for Binary Naive Bayes is "+(neg_f_measure+parseError));
		
		
		//clear training_data, testing_data and vocabulary here		
		training_data.clear();
		testing_data.clear();
		vocabulary.clear();
		
	}
	
	
}
