package day2;

import java.util.Objects;

public class Pick {

    private final int noRed;
    private final int noGreen;
    private final int noBlue;

    Pick(final int noRed, final int noGreen, final int noBlue) {
        this.noRed = noRed;
        this.noGreen = noGreen;
        this.noBlue = noBlue;
    }

    public int getNoRed() {
        return noRed;
    }

    public int getNoGreen() {
        return noGreen;
    }

    public int getNoBlue() {
        return noBlue;
    }


    @Override
    public int hashCode() {
        return Objects.hash(noBlue, noGreen, noRed);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pick other = (Pick) obj;
        return noBlue == other.noBlue && noGreen == other.noGreen && noRed == other.noRed;
    }

}
