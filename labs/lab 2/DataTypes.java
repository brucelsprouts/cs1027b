public class DataTypes {
    public int intVar;
    public double doubleVar;
    public char charVar;
    public boolean boolVar;
    public ClassA varA;

    public DataTypes() {
    }

    public DataTypes(int newIntVar, double newDoubleVar, char newCharVar, boolean newBoolVar, ClassA newVarA) {
        intVar = newIntVar;
        doubleVar = newDoubleVar;
        charVar = newCharVar;
        boolVar = newBoolVar;
        varA = newVarA;
    }

    public boolean equals(DataTypes other) {
        if (this.intVar != other.intVar || this.doubleVar != other.doubleVar ||
            this.charVar != other.charVar || this.boolVar != other.boolVar) {
            return false;
        }

        return this.varA.equals(other.varA);
    }
}