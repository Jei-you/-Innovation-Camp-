public class Bus {
    int number = 0 ;    //번호
    int oil = 100;        //주유량
    int totalOil = 100;     //총 주유량

    int speed = 0;      //속도
    //    int speedChange = 0 ;   //속도 변경
    int totalPas = 0;//총승객수
    int curPas = 0; //탑승한 승객수
    int maxPas = 30; //최대승객수
    String status = ""; //상태
    int totalFee = 0;    //총요금
    int fee = 1000;    //요금

    public Bus (int busNum){  //생성자 : 클래스가 객체로 만들어질 때 변수들 값 세팅
        this.number = busNum;
        this.status = "운행 중";
    }
    //탑승
    public void take(int curPas){
        if ((this.curPas + curPas)>this.maxPas){
            System.out.println("만원입니다.");
        }else {
            this.curPas += curPas;
            this.totalFee += curPas * this.fee;

        }
    }
    //상태변경
    public void statusChange(int oilMinus) {

        if (50<=this.totalOil&&this.totalOil<=100){
            System.out.println("운행중");
        }
        if ( 5 < this.totalOil && this.totalOil<= 50) {
            System.out.println("차고지행"); //상태 운행 -> 차고지행 변경
        } else {
            System.out.println("경고 : 주유 필요"); //기름 5이하 떨어지면 알럿
            this.totalOil = this.oil - oilMinus;
        }

        }


      //속도변경
    public void speedChange(int plusSpeed){
        if(this.oil>=10 ){
            speed += plusSpeed;
        }else{
            System.out.println("경고 : 주유량 확인 필요");
        }
    }
    public void showInfo(){
        System.out.println("버스 번호 : " + this.number);
        System.out.println("a.탑승 승객수 : " + this.curPas);
        System.out.println("b.잔여 승객수 : " + (this.maxPas-this.curPas));
        System.out.println("c.요금확인 : " + this.totalFee);
        System.out.println("e.주유량 : " + this.totalOil);

    }
}
