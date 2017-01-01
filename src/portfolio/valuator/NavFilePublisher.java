package portfolio.valuator;

import java.util.Scanner;

/**
 * Created by williamxuxianglin on 31/12/16.
 */
public class NavFilePublisher implements Runnable{
    private Thread t;
    private String name;
    private PortfolioFileWriter fileWriter;
    private PortfolioNavPrinter navPrinter;

    public NavFilePublisher() {
        this.name = "Nav file publish";
        this.fileWriter = new PortfolioFileWriter();
        this.navPrinter = new PortfolioNavPrinter();
    }

    @Override
    public void run() {

        Scanner commandReader = new Scanner(System.in);

        while(true){
            if("p".equals(commandReader.next())){
                fileWriter.writeToFile(navPrinter.printNavBreakdown());
            }
        }
    }

    public void start(){
        System.out.println("To print out latest summary of your position, press \"p\"");

        if(t==null){
            t = new Thread(this, name);
            t.start();
        }
    }
}
