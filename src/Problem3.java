public class Problem3 {

    public static void main(String[] args) {
        int[] A = {1,2,3,4,5,6,7,8};
        sortArray(A);
        System.out.println(splitEqualAverage(A));

    }

    private static void sortArray(int[] array){

        for(int i = 0; i < array.length - 1; i++){

            int indexMin = i;

            for(int j = i + 1; j < array.length; j++){
                if(array[j] < array[indexMin]){
                    indexMin = j;
                }
            }

            int aux = array[indexMin];
            array[indexMin] = array[i];
            array[i] = aux;

        }
    }

    private static boolean splitEqualAverage(int[] A){

        int sum = computeSum(A);

        int half = sum / 2;

        if(half % 2 != 0){ // if half of the sum is not even, we cannot split the array
            return false;
        }

        if(sum / A.length == A[0]){ // if each number in the array is the same, we can split it
            return true;
        }

        int[] B = new int[30];
        int[] C = new int[30];

        B[0] = A[A.length - 1]; //last element from A is placed in the B array
        C[0] = A[A.length - 2]; //last element - 1 from A is placed in the C array

        int indexB = 1;
        int indexC = 1;

        int sumB = B[0]; //initialize the sums of B and C arrays
        int sumC = C[0];

        for(int i = A.length - 3; i >= 0; i--){ //iterate the array backwards

            if(sumB + A[i] <= half && half - (sumC + A[i]) <= 0 ){ // if the we add the number from A[i] to the sumB and the
              sumB += A[i];                                        // resulting sum is less or equal than the half and
              B[indexB] = A[i];                                    // A[i] cannot be added to the sumC because it exceeds the half
              indexB ++;                                           // then we add it to B array
            }else{
              sumC += A[i];
              C[indexC] = A[i];                                    // else we add it to C array
              indexC ++;
            }
        }

        indexB++;
        indexC++;

        float avgB = (float)sumB / (indexB);
        float avgC = (float)sumC / (indexC);

        return avgB == avgC;
    }

    private static int computeSum(int[] array){
        int sum = 0;
        for (int j : array) {
            sum += j;
        }
        return sum;
    }

}
