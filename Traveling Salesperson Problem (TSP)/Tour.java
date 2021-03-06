public class Tour {

    private class Node {
        private Point p;
        private Node next;
    }

    Node first = new Node();

    public Tour() {
        first.next = first;
        first.p = null;
    }

    public Tour(Point a, Point b, Point c, Point d) {
        Node first = new Node();
        Node second = new Node();
        Node third = new Node();
        Node fourth = new Node();

        //1>2
        first.p = a;
        first.next = second;
        //2>3
        second.p = b;
        second.next = third;
        //3>4
        third.p = c;
        third.next = fourth;
        //4>1
        fourth.p = d;
        fourth.next = first;

    }

    public int size() {
        if (first.p == null) {
            return 0;
        }

        Node temp = first;
        int n = 1;


        do {
            n++;
            temp = temp.next;
        } while (temp.next != first);

        return n;
    }

    public double length() {
        if (first.p == null) {
            return 0;
        }

        Node temp1 = first;
        Node temp2 = first.next;
        double distance = 0.0;

        do {
            distance += temp1.p.distanceTo(temp2.p);

            temp1 = temp1.next;
            temp2 = temp2.next;
        } while (temp1 != first);

        return distance;
    }

    public String toString() {
        Node temp = first;
        StringBuilder output = new StringBuilder();

        do {
            output.append(temp.p.toString() + "\n");
            temp = temp.next;
        } while (temp != first);

        return output.toString();
    }

    public void draw() {
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);

        Node temp1 = first;
        Node temp2 = first.next;

        do {
            temp1.p.drawTo(temp2.p);

            temp1 = temp1.next;
            temp2 = temp2.next;
        } while (temp1 != first);

    }

    public void insertNearest(Point p) {
        if (size() == 0) {
            first.p = p;
            first.next = first;
            return;
        }

        double minDistance = Double.POSITIVE_INFINITY;
        Node closestNode = null;
        Node temp = first;
        double distance;

        do {
            distance = temp.p.distanceTo(p);

            if (distance < minDistance) {
                minDistance = distance;
                closestNode = temp;
            }

            temp = temp.next;
        } while (temp != first);

        Node newNode = new Node();
        newNode.p = p;
        newNode.next = closestNode.next;

        closestNode.next = newNode;
    }

    public void insertSmallest(Point p) {
        if (size() == 0) {
            first.p = p;
            first.next = first;
            return;
        }

        double leastDistance = Double.POSITIVE_INFINITY;
        Node smallestNode = null;
        Node temp = first;
        double distance;

        do {
            distance = temp.p.distanceTo(p) + temp.next.p.distanceTo(p) - temp.p
                    .distanceTo(temp.next.p);

            if (distance < leastDistance) {
                leastDistance = distance;
                smallestNode = temp;
            }

            temp = temp.next;
        } while (temp != first);

        Node newNode = new Node();
        newNode.p = p;
        newNode.next = smallestNode.next;

        smallestNode.next = newNode;
    }


    public static void main(String[] args) {
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);

        Tour squareTour = new Tour(a, b, c, d);

        int size = squareTour.size();
        StdOut.println("Number of points = " + size);
        double length = squareTour.length();
        StdOut.println("Tour length = " + length);
        StdOut.println(squareTour);

        squareTour.draw();
    }
}
