/* Given an m x n integers matrix, return the length of the longest increasing path in matrix. From each cell, you 
can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the 
boundary (i.e., wrap-around is not allowed).
* Eg 1 :  grid = [[9,9,4],[6,6,8],[2,1,1]]        Output = 4 
* Eg 2 :  grid = [[3,4,5],[3,2,6],[2,2,1]]        Output = 4 
* Eg 3 :  grid = [[1]]                            Output = 1 
 */
public class LongestPath
{
      public int LongestIncreasingPath(int grid[][])
      {
            if((grid.length == 0) && (grid[0].length == 0)) return -1;    // No Path possible...
            int descendDP[][] = new int[grid.length][grid[0].length];   //* DP Array Decreasing -> O(N x M)
            int ascendDP[][] = new int[grid.length][grid[0].length];    //* DP Array Increasing -> O(N x M)
            descendDP[0][0] = 1; ascendDP[0][0] = 1;     // base DP conditions set...
            for(int i = 0; i < ascendDP.length - 1; i++)      //! DP First Row Initialisation -> O(N)
            {
                  if(grid[0][i + 1] > grid[0][i])     // If next number is strictly greater...
                        ascendDP[0][i + 1] = ascendDP[0][i] + 1;
                  else
                        ascendDP[0][i + 1] = 1;
                  if(grid[0][i + 1] < grid[0][i])     // If next number is strictly lesser...
                        descendDP[0][i + 1] = descendDP[0][i] + 1;
                  else
                        descendDP[0][i + 1] = 1;
            }
            for(int i = 0; i < ascendDP[0].length - 1; i++)    //! DP First Column Initialisation -> O(M)
            {
                  if(grid[i + 1][0] > grid[i][0])     // If next number is strictly increasing...
                        ascendDP[i + 1][0] = ascendDP[i][0] + 1;
                  else
                        ascendDP[i + 1][0] = 1;
                  if(grid[i + 1][0] < grid[i][0])     // If next number is strictly decreasing...
                        descendDP[i + 1][0] = descendDP[i][0] + 1;
                  else
                        descendDP[i + 1][0] = 1;
            }
            for(int i = 1; i < grid.length; i++)     //! Grid Traversal using DP Matrices -> O(N x M)
            {
                  for(int j = 1; j < grid[0].length; j++)
                  {
                        if((grid[i][j] > grid[i - 1][j]) && (grid[i][j] > grid[i][j - 1]))   // If both are lower...
                              ascendDP[i][j] = Math.max(ascendDP[i - 1][j], ascendDP[i][j - 1]) + 1;
                        else if(grid[i][j] > grid[i - 1][j])    // If Upper number is lower...
                              ascendDP[i][j] = ascendDP[i - 1][j] + 1;
                        else if(grid[i][j] > grid[i][j - 1])    // If left number is lower...
                              ascendDP[i][j] = ascendDP[i][j - 1] + 1;
                        else       // If none are lower...
                              ascendDP[i][j] = 1;
                        if((grid[i][j] < grid[i - 1][j]) && (grid[i][j] < grid[i][j - 1]))    // if both are higher...
                              descendDP[i][j] = Math.max(descendDP[i - 1][j], descendDP[i][j - 1]) + 1;
                        else if(grid[i][j] > grid[i - 1][j])     // If Upper number is higher...
                              descendDP[i][j] = descendDP[i - 1][j] + 1;
                        else if(grid[i][j] > grid[i][j - 1])     // If left number is higher...
                              descendDP[i][j] = descendDP[i][j - 1] + 1;
                        else       // If none are higher...
                              descendDP[i][j] = 1;
                  }
            }
            int maxAscend = 1, maxDescend = 1;     //* Variable Declaration -> O(1)
            for(int i = 0; i < grid.length; i++)     //! Grid Traversal -> O(N x M)
            {
                  for(int j = 0; j < grid[0].length; j++)
                  {
                        maxAscend = Math.max(maxAscend, ascendDP[i][j]);    // Maximum Decreasing Sequence...
                        maxDescend = Math.max(maxDescend, descendDP[i][j]);   // Maximum Increasing Sequence...
                  }
            }
            // When a decreasing sequence read in reverse Order, it becomes an Increasing Sequence...
            return (maxAscend > maxDescend) ? maxAscend : maxDescend;    // Ternary Conditional Statement...
      }
      public static void main(String args[])
      {
            //? Test Case - I 
            int array[][] ={{9,9,4}, {6,6,8}, {2,1,1}};
            //? Test Case - II 
            int array1[][] = {{3,4,5}, {3,2,6}, {2,2,1}};
            //? Test Case - III
            int array2[][] ={{1}};
            LongestPath longestPath = new LongestPath();     // Object creation...
            System.out.println("Longest Increasing Path for Test Case I : "+longestPath.LongestIncreasingPath(array));
            System.out.println("Longest Increasing Path for Test Case II : "+longestPath.LongestIncreasingPath(array1));
            System.out.println("Longest Increasing Path for Test Case III : "+longestPath.LongestIncreasingPath(array2));
      }
}



//! Time Complexity -> O(N x M)
//* Space Complexity -> O(N x M)