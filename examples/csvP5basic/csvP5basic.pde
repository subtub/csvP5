/**
 * csvP5basic
 *
 * csvP5 http://www.github.com/subtub/csvP5
 */

import de.fhpotsdam.io.csv.*;

CsvP5 csv;
  
csv = new CsvP5(this, "sample.csv");
csv.load();

println("CSV Table");
for(int col=0; col<csv.getTotalColumns(); col++) {
  for(int row=0; row<csv.getTotalRows(); row++) {
    print(csv.getString(row, col)+"  ");
  }
  println();
}

println("\nCSV Information");
println("Is complete         =   " + csv.isComplete());
println("Separator           =   " + csv.getSeparator());
println("Comment Indicator   =   " + csv.getCommentIndicator());
println("Total columns       =   " + csv.getTotalColumns());
println("Total rows          =   " + csv.getTotalRows());
//println("Smallest Value    =   " + csv.getMin());
//println("Biggest Value     =   " + csv.getMax());

exit();

