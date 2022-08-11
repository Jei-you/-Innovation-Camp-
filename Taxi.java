public class Taxi {
    Taxi(){this.status = "일반";}
    String depart = "";        //목적지
    int departOfDis = 0;    //목적지까지 거리
    int basicDis = 1;       //기본거리
    int basicFee = 3000;       //기본요금
    int DisOfFee = 1000;       //거리당 요금

    int number = 0 ;    //번호
    int oil = 100;        //주유량
    int oilMinus = 0;
    int totalOil = 100;     //총 주유량

    int speed = 0;      //속도
    int totalSpeed = 0;
    int speedChange = 0 ;   //속도 변경
    int totalPas = 0;//총승객수
    int curPas = 0; //탑승한 승객수
    int maxPas = 4; //최대승객수
    String status = ""; //상태
    int totalFee = 0;    //총요금

    public Taxi (int taxiNum){  //생성자 : 클래스가 객체로 만들어질 때 변수들 값 세팅
        this.number = taxiNum;
        this.status = "일반";
        this.depart = "서울역";
        this.departOfDis = 2;
    }
    //탑승
    public void take(int curPas){
        if ((this.curPas + curPas)>this.maxPas){
            System.out.println("만원입니다.");
        }else {
            this.curPas += curPas;
        }
    }
    //요금책정
    public void Fee(int departOfDis ) {
        this.totalFee += this.basicFee + (departOfDis * this.DisOfFee);
    }
    //상태변경
        public void statusChange(int oilMinus) {
            if (50<=this.totalOil&&this.totalOil<=100){
                System.out.println("운행중");
            }
            if ( 5 < this.totalOil && this.totalOil<= 50) {
                System.out.println("차고지행"); //상태 운행 -> 차고지행 변경
            } else {
                System.out.println("경고 : 주유 필요, 운행불가"); //기름 5이하 떨어지면 알럿
                this.totalOil = this.oil - oilMinus;
            }
        }
    //속도변경
    public void speedChange(int plusSpeed){
    this.totalSpeed = this.speed + plusSpeed;
        }

    public void showInfo(){
        System.out.println("택시 번호 : " + this.number);
        System.out.println("a.탑승 승객수 : " + this.curPas);
        System.out.println("b.잔여 승객수 : " + (this.maxPas-this.curPas));
        System.out.println("c.지불할요금 : " + this.totalFee);
        System.out.println("d.주유량 : " + this.totalOil);
        System.out.println("e.운행상태 : " + this.oilMinus);

    }
}
