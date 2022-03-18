import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Collections;

public class DriversStats extends JFrame implements ActionListener,Serializable {   //class use to show all drivers statistics
    Formula1ChampionshipManager driverObj=new Formula1ChampionshipManager();        //object of Formula1ChampionshipManager
    JButton backBtn;
    JTable statsTable;


    DriversStats(){
        driverObj.readFile();
        JPanel panel=new JPanel();
        panel.setBounds(0,0,700,500);
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BorderLayout());

        statsTable =statTable();
        statsTable.setEnabled(false);
        statsTable.getTableHeader().setReorderingAllowed(false);
        statsTable.getTableHeader().setResizingAllowed(false);
        statsTable.getTableHeader().setEnabled(false);
        statsTable.setBounds(0,0,700,500);


        //Back Button properties
        backBtn=new JButton("Back");
        backBtn.setBounds(10,280,100,30);
        backBtn.addActionListener(this);
        this.add(backBtn);
        backBtn.setFocusable(false);

        JScrollPane pane=new JScrollPane(statsTable);
        panel.add(pane);

        this.add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(700,500);
        this.setResizable(false);

}
        JTable statTable(){     //table use to show all drivers stats

            String[][] driverData = new String[driverObj.driversList.size()][7];    //initializing a 2nd array with length odf 7
            String[] columns = {"Driver Name", "Driver Team", "Total Races","Wins","2nd","3rd","Points"};   //including columns
            Collections.sort(driverObj.driversList);        //sorting in descending order

            for (int i = 0; i < driverObj.driversList.size(); i++) {        //for loop to get data from driversList
                String driverName = driverObj.driversList.get(i).getDriverName();
                String driverTeam = driverObj.driversList.get(i).getDriverTeam();
                String totalRaces = String.valueOf(driverObj.driversList.get(i).getTotalRaces());
                String wins=String.valueOf(driverObj.driversList.get(i).getFirstPositions());
                String second=String.valueOf(driverObj.driversList.get(i).getSecondPositions());
                String third=String.valueOf(driverObj.driversList.get(i).getThirdPositions());
                String points=String.valueOf(driverObj.driversList.get(i).getTotalPoints());
                String[] drivers = {driverName, driverTeam,totalRaces,wins,second,third,points};
                driverData[i] = drivers;    //adding to the 2D array
            }
            return statsTable =new JTable(driverData,columns);  //adding data to the table
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backBtn){        //added actionListener to backBtn to go back to the main gui
            F1ManagerGUI homeMenu=new F1ManagerGUI();
            this.dispose();
        }

    }
}
