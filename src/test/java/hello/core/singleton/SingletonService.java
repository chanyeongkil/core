package hello.core.singleton;

public class SingletonService {
    /** Application 실행 시점에 static영역에 해당 객체가 초기화 되어 생성 됨 **/
    private static final SingletonService instance = new SingletonService();

    /** private로 선언되어 외부에서 객체 생성 불가(==new 키워드로 객체생성 불가) **/
    private SingletonService(){}

    /** 객체 인스턴스가 필요하면 이 메서드를 호출하여 사용, 항상 같은 객체를 반환 함 **/
    public static SingletonService getInstance(){
        return instance;
    }

    public void logic(){
        System.out.println("singleton 객체 로직 !!");
    }
}
