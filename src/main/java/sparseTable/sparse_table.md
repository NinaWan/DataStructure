# Sparse Table

## 解决的问题

Range Query - fast queries on a set of data which won't change while processing these queries

e.g. Range Maximum Query

Given an array nums[], answer queries to find the maximum elements of nums[l, r].

## Intuition

1个非负整数一定可以由1个或者多个2的幂数相加而得，即可以唯一地由几个2的次方的和表示。这由整数的二进制表示衍生而来。正整数x最多由$[\log_{2}{x}]$个正整数(i.e. 2的次方)相加而得。

Similarly, an interval can be uniquely represented by the union of one or multiple non-overlapping sub-intervals whose lengths are powers of 2.

## Basic Idea

Precompute the target values (e.g. maximum/minimum element) for all subarrays whose length are powers of 2;

and

Store them in a table so that any range query can be answered efficiently.

This table that stores data is called a `sparse table`.

Sparse table is a 2-dimensional array, let lookup[i][j] represent the target value of nums[i, i+2<sup>j</sup>-1], i.e. the subarray that starts from i with length 2<sup>j</sup>. Particularly, i $\in$ [0, n) and j $\in$ [0, $\log_{2}{n}$].

## How to build the lookup table

lookup[i][j] = combine the results from lookup[i][j-1] and lookup[i+2<sup>j-1</sup>][j-1]

Particularly, lookup[i][0] = f(nums[i]).

## How to answer queries with lookup table

`Basic Idea`

按照上述特性，将目标区间[l, r]分割成一个个子区间，每个子区间的长度为2的幂数。根据f函数，整合所有子区间的结果，即为目标区间最终的结果。

`Special for Range Maximum/Mininum/GCD Query`

对于Range Sum Query问题，必须将目标区间分割成若干连续且不重叠的子区间才能确保最终结果的正确性。而对于Range Maximum/Minimum/GCD Query这类问题则没有这个硬性要求，只要能确保所有子区间合起来能够覆盖整个目标区间即可，子区间之间是否有重叠部分并不影响最终结果的正确性。因此，对于此类问题，只需要整合[l, l+2<sup>j</sup>-1]和[r-2<sup>j</sup>+1, r]两个子区间的结果即可，其中 j = floor($\log_{2}{r-l+1}$)。这也是保证query时间复杂度为常数的关键。

For query(l, r), let j = floor($\log_{2}{r-l+1}$), thus (r-l+1)/2 <= 2<sup>j</sup> <= r-l+1.

Then, we need to choose from target values in nums[l, l+2<sup>j</sup>-1] and nums[l+2<sup>j</sup>, r], which can be covered by lookup[l][j] and lookup[r-2<sup>j</sup>+1][j], i.e. the first and last 2<sup>j</sup> elements of nums[l, r].

## 复杂度

### 时间复杂度

build: O(n*$\log{n}$)

query: Some range queries like range minimum/maximum/gcd query can be answered in constant time with sparse table, but others like range sum query can only be answered in O($\log{n}$) time. This depends on whether the f function is idempotent.

### 空间复杂度

O(n*$\log{n}$)

## Problem list

* Range Maximum/Minimum Query
* Range GCD Query
* Range Sum Query

## Refs

1. [Sparse Table](https://www.geeksforgeeks.org/sparse-table/)
2. [Range maximum query using Sparse Table](https://www.geeksforgeeks.org/range-maximum-query-using-sparse-table/)
3. [Sparse Table - Algorithms for Competitive Programming](https://cp-algorithms.com/data_structures/sparse-table.html)