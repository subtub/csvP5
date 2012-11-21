/**
 * csvP5mathUtils
 *
 * csvP5 http://www.github.com/wrongentertainment/csvP5
 */

import de.fhpotsdam.io.csv.*;
import de.fhpotsdam.util.math.*;

CsvP5 csv;
//Math math;


void setup() {
  size(500, 500);
  
  csv = new CsvP5(this, "sample.csv");
  csv.load();
  println("csv total columns   =   " + csv.getColumnCount());
  println("csv total rows      =   " + csv.getRowCount());
  
  PFont font = createFont("", 12);
  textFont(font);
}


void draw() {
  background(255);
  
  for(int col=0; col<csv.getColumnCount(); col++) {
    for(int row=0; row<csv.getRowCount(); row++) {
      fill(0);
      text(csv.getString(row, col)+" ("+"%)", 50+(col*75), 50+(row*20));
    }
  }
  
  line(410, 35, 410, 395);
  line(45, 450, 370, 450);
  fill(70, 140, 210);
  text("sum", 15, 180);
  for(int col=0; col<csv.getRowCount(); col++) {
    //int sum = int(csv.math.getSum(csv.data, col, 0, csv.getColumnCount()));
    text(sum, 50+(col*75), 180);
  }
  text("sum", 430, 30);
  for(int row=0; row<csv.getRowCount(); row++) {
    int sum = int(csv.math.getSum(csv.data[row], 0, csv.getColumnCount()));
    text(sum, 430, 50+(row*20));
  }
  
}
