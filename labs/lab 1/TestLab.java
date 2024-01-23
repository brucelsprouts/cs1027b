public class TestLab {
    public static void main(String args[]){
        Player p1 = new Player("Bruce","goalie",72);
        Player p2 = new Player("bruce","defence",27);

        System.out.println(p1.getName());
        System.out.println(p1.getPosition());
        System.out.println(p1.getJerseyNum());

        p1.setName("bruce");
        p1.setPosition("defence");
        p1.setJerseyNum(27);

        System.out.println(p1.getName());
        System.out.println(p1.getPosition());
        System.out.println(p1.getJerseyNum());

        System.out.println(p1);

        if (p1.equals(p2)) {
            System.out.println("Same player");
           } else {
            System.out.println("Different player");
           }
    }
}
