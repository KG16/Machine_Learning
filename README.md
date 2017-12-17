# Machine Learning assignment
Solution codes for the course BITS F464 Machine Learning assignments


## Candidate Elimination for dataset-zoo
After the implementation of the Candidate Elimination Algorithm based on the given training data with 7 classes, we generated the following data. (Here -1 represents ‘?’. The other values are in accordance with the given notation. -2 is used to represent ‘Null’.)
As can be seen from below, the concept cannot be learned for classes 3 and 7 from the given data.
For classes 1, 2,4,5,6 the concept can be learned and the boundaries are given below.
CountG is the number of Generic Boundaries for the given class. Similarly, CountS is for the number of specific Boundaries.
This data gives us the set of Generic and Specific Boundaries for each class (if applicable):
Program successfully completed!!!
Specific boundaries for class 1(countS=1) :
-1, 0, -1, 1, -1, -1, -1, -1, 1, 1, 0, -1, -1, -1, -1, -1, 

Specific boundaries for class 2(countS=1) :
0, 1, 1, 0, -1, -1, -1, 0, 1, 1, 0, 0, 2, 1, -1, -1, 
Specific boundaries for class 3(countS=0) :

Specific boundaries for class 4(countS=1) :
0, 0, 1, 0, 0, 1, -1, 1, 1, 0, -1, 1, 0, 1, -1, -1, 

Specific boundaries for class 5(countS=1) :
0, 0, 1, 0, 0, 1, -1, 1, 1, 1, -1, 0, 4, -1, 0, 0, 

Specific boundaries for class 6(countS=1) :
-1, 0, 1, 0, -1, 0, -1, 0, 0, 1, -1, 0, 6, 0, -1, 0, 

Specific boundaries for class 7(countS=0) :


Generic boundaries for class 1(countG=1) :
-1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 

Generic boundaries for class 2(countG=7) :
-1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, -1, -1, -1, 
-1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, -1, -1, -1, 
-1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, 2, -1, -1, -1, 
-1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, 2, -1, -1, -1, 
-1, -1, -1, 0, -1, -1, -1, 0, -1, -1, -1, 0, -1, 1, -1, -1, 
-1, -1, -1, 0, -1, -1, -1, -1, -1, -1, 0, 0, -1, 1, -1, -1, 

Generic boundaries for class 3(countG=8) :
0, 0, -1, -1, -1, -1, 1, -1, 1, 1, -1, 0, -1, -1, -1, -1, 
0, 0, -1, -1, -1, -1, 1, -1, -1, 1, 1, 0, -1, -1, -1, -1, 
0, 0, -1, -1, -1, -1, 1, -1, -1, 1, -1, 0, 4, -1, -1, -1, 
-1, 0, -1, 0, -1, -1, -1, -1, 1, -1, -1, 0, -1, -1, -1, 1, 
-1, 0, -1, 0, -1, -1, -1, -1, -1, -1, -1, 0, -1, 1, -1, 1, 
-1, -1, 1, -1, -1, 1, -1, -1, -1, -1, -1, 0, -1, 1, -1, 1, 
-1, -1, -1, 0, -1, 1, -1, -1, 1, -1, -1, 0, -1, -1, -1, 1, 
-1, -1, -1, 0, -1, 1, -1, -1, -1, -1, -1, 0, -1, 1, -1, 1, 

Generic boundaries for class 4(countG=14) :
-1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 
-1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 
-1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, 1, -1, -1, -1, -1, 
-1, -1, 1, -1, -1, 1, -1, 1, -1, -1, -1, -1, 0, -1, -1, -1, 
-1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, -1, 0, -1, -1, -1, 
-1, -1, 1, 0, 0, 1, -1, 1, -1, -1, -1, -1, -1, 1, -1, -1, 
0, 0, 1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, 
-1, 0, 1, -1, -1, 1, -1, 1, -1, -1, -1, -1, -1, 1, -1, -1, 
-1, 0, 1, 0, -1, 1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, 
-1, -1, 1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 0, 1, -1, -1, 
-1, -1, -1, -1, -1, -1, -1, 1, -1, 0, -1, -1, -1, -1, -1, -1, 
-1, -1, -1, -1, -1, -1, -1, -1, 1, 0, -1, -1, -1, -1, -1, -1, 
-1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, 1, -1, -1, 
-1, 0, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, -1, -1, 

Generic boundaries for class 5(countG=32) :
0, -1, -1, -1, -1, 1, -1, 1, -1, 1, -1, 0, -1, -1, -1, -1, 
-1, -1, 1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, -1, -1, -1, 
-1, -1, 1, -1, -1, 1, -1, 1, -1, -1, -1, 0, -1, -1, -1, -1, 
-1, -1, -1, 0, -1, 1, -1, 1, -1, 1, -1, -1, -1, -1, -1, -1, 
0, 0, 1, -1, -1, 1, -1, -1, 1, -1, -1, 0, -1, -1, -1, -1, 
-1, 0, 1, 0, -1, 1, -1, -1, 1, -1, -1, 0, -1, -1, -1, -1, 
0, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, 4, -1, -1, -1, 
-1, 0, 1, -1, -1, 1, -1, -1, 1, -1, -1, 0, -1, -1, -1, 0, 
-1, -1, 1, -1, -1, 1, -1, 1, -1, -1, -1, -1, 4, -1, -1, -1, 
0, 0, 1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 
0, -1, -1, -1, -1, 1, -1, -1, 1, -1, -1, -1, 4, -1, -1, -1, 
-1, -1, -1, 0, -1, 1, -1, 1, -1, -1, -1, -1, 4, -1, -1, -1, 
-1, -1, 1, -1, 0, 1, -1, -1, 1, -1, -1, 0, -1, -1, -1, 0, 
-1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, 4, -1, -1, 0, 
-1, -1, -1, -1, -1, 1, -1, -1, 1, -1, -1, -1, 4, -1, -1, 0, 
0, -1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, 4, -1, -1, -1, 
-1, -1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, 4, -1, -1, 0, 
-1, -1, -1, 0, -1, 1, -1, -1, 1, -1, -1, -1, 4, -1, -1, -1, 
-1, -1, -1, 0, -1, 1, -1, -1, -1, 1, -1, -1, 4, -1, -1, -1, 
0, 0, -1, -1, -1, 1, -1, -1, -1, 1, -1, 0, -1, -1, -1, -1, 
-1, 0, -1, 0, -1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 
-1, 0, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, 0, 
-1, -1, -1, -1, 0, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, 0, 
-1, -1, -1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, -1, -1, 0, 
-1, -1, -1, -1, -1, 1, 0, -1, -1, -1, -1, -1, 6, -1, -1, -1, 
-1, -1, -1, -1, -1, -1, 0, -1, -1, 0, -1, -1, 6, -1, -1, -1, 
-1, -1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, 6, -1, -1, -1, 
-1, -1, -1, -1, -1, -1, 1, -1, -1, 1, -1, -1, 6, -1, -1, -1, 
0, 0, -1, -1, -1, -1, 0, -1, -1, 1, -1, -1, -1, 1, -1, -1, 
0, 0, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1, 0, 
0, -1, -1, -1, 0, -1, 0, -1, 1, 1, -1, -1, -1, -1, -1, -1, 
0, -1, -1, -1, 0, -1, 0, -1, -1, 1, -1, -1, -1, 1, -1, -1, 

Generic boundaries for class 6(countG=3) :
-1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, 6, -1, -1, -1, 
-1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, 6, -1, -1, -1, 
-1, -1, -1, 0, -1, 0, -1, -1, -1, -1, -1, 0, -1, 0, 0, -1, 

Generic boundaries for class 7(countG=0) :


## Decision Tree learning for dataset-adult
Results
	    Accuracy	Precision	Recall	F-Measure	Run-Time
ID3	0.8103925	0.5974326	0.6050442	0.60121435	93 secs
Random Forest
(with 10 trees)	0.8108839	0.596381	0.6170047	0.60651755	3 secs
Random Forest
(with 50 trees)	0.82722193	0.6373305	0.62324494	0.63020897	15 secs
Random Forest
(with 100 trees)	0.8296173	0.6395107	0.63884556	0.6391779	33 secs
Pruned Tree 
(1 node pruned)	0.82396656	0.6394422	0.58424336	0.61059785	426 secs
Pruned Tree
(2 nodes pruned)	0.83090717	0.66231066	0.5798232	0.618328	809 secs
Pruned Tree
(3 nodes pruned)	0.8332412	0.68608093	0.5421217	0.6056645	1041 secs
					

Interpretation of results
	As can be clearly seen, ID3 by itself does not give a very high accuracy when tested on validation data due to overfitting. 
	This accuracy is increased in the case of both, random-forests and reduced error pruning.
	Further, the more trees we consider in random-forests, the more the accuracy of the result is increased. This can be seen when number of trees change from 10 to 50 to 100.
	In the case of reduced error pruning the accuracy keeps increasing as we prune more nodes. But, to get the final pruned tree (after having pruned all possible nodes), a lot of time is consumed.
	Hence, in reduced error pruning we have mentioned only 3 cases for comparison, when 1, 2 and 3 nodes have been pruned and the algorithm is stopped. As we can clearly see, even for pruning just 3 nodes, a LOT of time is consumed.


Applications ID3
	ID3 is used in computer crime forensics. 
	ID3 is ideal for situations where the dataset is not large enough to make use of tree pruning.
	When less data is available, we cannot create a separate validation set. If this is done the training examples used for ID3 will decrease significantly.
	Some applications are finding the cause of equipment malfunctions, medical diagnosis, etc.
Applications of Random Forest
	ID3 tree tends to overfit the training data giving us incorrect predictions.
	In such cases, random forest is a good solution.
	Random forest does not need a large dataset as training data and additionally gives the result as the mode of many trees generated randomly on the same dataset.
	Random forests are used in radar, LiDAR , thermal remote sensing imagery and other applications in remote sensing.


Applications of Reduced-Error Pruning
	Reduced error is one of the ideal solutions when a large dataset of training examples is available.
	About one third of the training examples are randomly selected to get reserved as the validation set and the other two-thirds is used to train and form the tree using ID3.
	This is an effective method to deal with overfitting in ID3, as the final tree in ID3 is pruned till the point where further pruning will decrease its accuracy over the validation set.
	It is one of the leading techniques used in artificial neural networks.
	One direct application is “traffic incident detection” from previously available dataset.

## Naive Bayes 

Results
	Accuracy	Positive Precision	Positive Recall	Positive F-Measure	Negative Precision	Negative Recall	Negative F-Measure
Naïve Bayes	0.83872	0.84615	0.81328	0.82929	0.83200	0.86416	0.84768
Naïve Bayes + Removal of Stop Words	0.83139	0.84719	0.77776	0.81051	0.81867	0.88504	0.85016
Binary Naïve Bayes	0.82548	0.83107	0.80416	0.81732	0.82034	0.8468	0.83329

Interpretation of results
	The performance of the algorithm does not change significantly when Stop Words are removed. This is because, given the large number of  stop words in both the positive and negative training data and either of the positive or negative a-priori classification is equally likely, the probability of a stop word is only marginally different in both classifications. Hence, it does not contribute significantly to the calculation of a-posteriori probabilities. Intuitively, we can understand that that the sentiment of the words does not depend on Stop words.
	Binary Naïve Bayes also has an almost similar performance compared to the Normal Naïve Bayes classifier as each word tends to have a more or less common frequency of occurrence in all reviews. As a result, if a word occurring multiple times in a review is considered only once in the binary case, in the other reviews also it will occur only once. If it occurs multiple times, the number of times it occurs per unit word remains the same. Hence performance is more or less the same.

# Team members
1.	Siddhant Shenoy (2015A7PS0057H)
2.	Kriti Goyal (2015A7PS0100H)
3.	Rohith Pulipaka (2015AAPS0153H)

