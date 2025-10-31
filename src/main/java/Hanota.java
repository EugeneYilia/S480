import java.util.List;

public class Hanota {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A, B, C, A.size());
    }

    public void move(List<Integer> A, List<Integer> B, List<Integer> C, int count) {
        if (count == 1) {
            C.add(A.removeLast());
        } else {
            move(A, C, B, count - 1);
            C.add(A.removeLast());
            move(B, A, C, count - 1);
        }
    }
}
