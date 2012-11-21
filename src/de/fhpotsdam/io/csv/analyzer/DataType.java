package de.fhpotsdam.io.csv.analyzer;

public enum DataType {
	FLOAT {
		public String toString() {
			return "FLOAT";
		}
	},
	INT {
		public String toString() {
			return "INT";
		}
	},
	STRING {
		public String toString() {
			return "STRING";
		}
	},
	BOOLEAN {
		public String toString() {
			return "BOOLEAN";
		}
	}
}
