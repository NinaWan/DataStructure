# Red-Black Tree

## 红黑树的性质

1. 红黑树是一颗[二叉搜索树](../../tree/binarySearchTree/binary_search_tree.md)；
2. 红黑树中节点分为红色和黑色两种；
3. 根节点是黑色的；
4. 叶子节点(i.e. 红黑树中的叶子节点指的是树尾端的Null节点)是黑色的；
5. 红色节点的孩子节点都是黑色的；
6. 从根节点到叶子节点的每条路径上的黑色节点的数量是相同的。

## 红黑树的操作

### 插入

### 搜索

### 删除

## 红黑树的应用

### 红黑树在Java中的应用

TreeMap的底层实现用的是红黑树。

JDK 8之后，HashMap的底层实现采用的是数组+链表/红黑树，当链表长度大于等于7时转化成红黑树。

# Refs

1. [Red/Black Tree Visualization](https://www.cs.usfca.edu/~galles/visualization/RedBlack.html)