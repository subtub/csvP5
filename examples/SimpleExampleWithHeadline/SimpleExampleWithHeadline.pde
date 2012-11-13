/**
 * Simple csvP5 example with headline
 */

import de.fhpotsdam.io.csv.*;

CsvP5 csv;


void setup() {
  size(700, 800);
  
  csv = new CsvP5(this, "someCsv.csv");
  csv.hasHeadline(true);
  csv.load();
  println("csv total columns = " + csv.getColumnCount());
  println("csv total rows = " + csv.getRowCount());
  
  PFont font = createFont("", 12);
  textFont(font);
}


void draw() {
  background(0);  
  for(int i=0; i<csv.getColumnCount(); i++) {
    text(csv.getHeadlineName(i), 40+(i*110), 40);
    for(int j=0; j<csv.getRowCount(); j++) {
      fill(255);
      text(csv.getString(j, i), 40+(i*110), 70+(j*15));
    }
  }
}

