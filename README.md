# android-java-graphics_matrix_sample
Simple Android sample written in Java language where different matrices are applied to an ImageView widget that is displaying a bitmap stored in the resources. The idea behind this sample is displaying some options on how the bitmap can be drawn just changing the matrix in the ImageView without modifying the underlying bitmap.

## Quick explanation about matrices
Matrixes are a mathematical tool where some numbers are arranged in rows and columns. A matrix can be multiplied by other matrix if, and only if, the number columns within the first matches the number of rows in the second. Multiplying both matrixes will result in a new matrix matching the same number of rows that the first matrix had and the number of columns of the second one. Matrix multiplication is not a commutative operation, which means that multiplying matrix A per matrix B will not result in the same matrix of multiplying B per A. In order words, the order of the matrixes when they are multiplied matters.

Positions within a space can be identified by coordinates. In a 2 dimensional space (plane), only 2 coordinates are required. Thus, a matrix of 2 rows and one column (1x2) could be created to identify that position. As explained before, a matrix of 2x2 (M) could be multiplied by the position matrix (V) such as M * V, resulting in a new matrix of 1x2, like V was. The new matrix can be then interpreted like a position in a plane.

When using position matrixes of 1x2 only matrixes of 2x2 can be applied in order to get the new position matrix of 1x2. That configuration allows scaling, and rotating among other operations.

As said at the beginning, matrixes are just a mathematical tool. We can consider that what we have behings is a multidimensional function.

## Quick explanation about how matrices are used in Android
Graphical hardware uses matrices of 3 rows and 3 columns (3x3) to change the coordinate system instead of matrixes of 2x2 whose last row is always (0 0 1). This way, adjusting properly the last number within the first and second rows, we also are allowed to performe traslation within the plane. Using matrixes, each point coordinate can be modified to a new position. That allows some image effects:
  * Zoom In and out
  * Rotation
  * Traslation
  * Flip vertically, horizontally or in both axis.
