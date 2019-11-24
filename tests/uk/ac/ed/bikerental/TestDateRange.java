import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDateRange {
    private DateRange dateRange1, dateRange2, dateRange3;

    @BeforeEach
    void setUp() throws Exception {
        // Setup resources before each test
        this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),
                LocalDate.of(2019, 1, 10));
        this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5),
                LocalDate.of(2019, 1, 23));
        this.dateRange3 = new DateRange(LocalDate.of(2015, 1, 7),
                LocalDate.of(2018, 1, 10));
    }

    // Sample JUnit tests checking toYears works
    @Test
    void testToYears1() {
        assertEquals(0, this.dateRange1.toYears());
    }

    @Test
    void testToYears3() {
        assertEquals(3, this.dateRange3.toYears());
    }

    @Test
    void testOverlapsTrue() {
        // TODO: check we can see when two date ranges overlap
    	 assertEquals(true,this.dateRange2.overlaps(dateRange1));
    	 assertEquals(true,this.dateRange3.overlaps(dateRange1));
    	 assertEquals(true,this.dateRange3.overlaps(dateRange2));
        //fail();
    }

    @Test
    void testOverlapsFalse() {
        // TODO: check we can see when two date ranges  don't overlap
    	 assertEquals(false,this.dateRange1.overlaps(dateRange2));
    	 assertEquals(false,this.dateRange1.overlaps(dateRange3));
    	 assertEquals(false,this.dateRange2.overlaps(dateRange3));
    	
        fail();
    }

    // TODO: put some of your own unit tests here
}
