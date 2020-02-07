package besmak1.vocap;

// Manage the counting of points
public class GameOneScore  {

    private int totalPoint =0;

    //This function have to increase the final score as a function of the attempts number
    //validateCode :
    // 0 --> 2sd attempt
    // 1 --> 1st attempt
    void setTotalPOint(int validationCode){
        if(validationCode==1){
            totalPoint+=10;
        } else{
            if(validationCode==0){
                totalPoint+=5;
            }
        }
        totalPoint++;
    }

    int getTotalPoint(){
        return totalPoint;
    }


}
