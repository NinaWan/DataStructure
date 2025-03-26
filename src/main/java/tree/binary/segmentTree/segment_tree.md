# Segment Tree 线段树

## 概述

Segment Tree 是一种二叉树数据结构，每一个节点存储对应数组区间的信息。父节点是两个子节点信息的融合。它可以提供高效的区间查询和更新，适用于求区间和、区间最小最大等区间相关的问题。

对于节点i，左子节点坐标为2*i+1，右子节点坐标为2*i+2，父节点坐标为(i-1)/2。

## 操作

### 区间查询

查询给定区间[l, r]的信息，i.e. 元素和、最大最小元素、异或值等。

### 局部更新

将原数组下标i处的数值更新为给定的数值val，更新线段树中对应区间的信息。

### 区间更新

更新原数组中给定区间[l, r]中的所有数值，更新线段树中对应区间的信息。

## Lazy Propagation (draft)

- 用于range update，之前是单点update
- 通常的做法：对[ql, qr]范围内的每个i调用一次update
- lazy propagation优化，使得update range faster
- 基本思想就是推迟/延迟一些update，等到必要的时候再update
- 如果一个tree node cover的range完全在update range里时，只update当前tree node的值，将对其子节点的更新推迟，并将update信息记录在专门的lazy数组里
- lazy数组长度与nodes数组相同，初始值为0，表示没有pending的update
- lazy[i]，表示在对nums[i]进行任何query前需要对nodes[i]增加的值
- 逻辑
    1. 如果nums[i]有pending update，先把它加到nodes[i]上
    2. 如果nodes[i] cover的range完全在[ql, qr]中
        1. update当前的node
        2. 推迟对其子节点的update
    3. 如果nodes[i] cover的range与[ql, qr]错开
        1. recur for left and right 子节点
        2. update nodes[i] with results from left and right 子节点
- query方法也要做相应的更改
    1. 先check是否有pending update
        1. 如果有，则更新node
        2. 后序逻辑与普通query相同

## 复杂度

### 时间复杂度

[//]: # (update: O&#40;logn&#41;)

[//]: # ()

[//]: # (query: O&#40;logn&#41;)

### 空间复杂度

## Details

* [Sum Segment Tree](sum_segment_tree.md)
* [Max Segment Tree](max_segment_tree.md)

# Refs

1. [Segment Tree](https://www.geeksforgeeks.org/segment-tree-data-structure/)