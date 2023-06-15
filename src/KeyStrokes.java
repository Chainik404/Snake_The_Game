public enum KeyStrokes {
    Up(38),
    Down(40),
    Left(37),
    right(39),
    ;
    private final int num;
    KeyStrokes(int i) {
        this.num = i;
    }
    public int get(){
        return this.num;
    }
}
