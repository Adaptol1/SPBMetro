import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


public class RouteCalculatorTest extends TestCase
{
    List <Station> routeWithoutConnections;
    List <Station> routeWithOneConnection;
    List <Station> routeWithTwoConnections;
    private Station vladivostokskaya;
    private Station uralskaya;
    private Station kavkazskaya;
    private Station pokrovskaya;
    private Station petraVelikogo;
    private Station tatarskaya;
    private Station ufimskaya;
    private Station bashkirskaya;
    private Station ekaterinburgskaya;
    private RouteCalculator routeCalculator;

    private StationIndex stationIndex = new StationIndex();

    @Override
    protected void setUp() throws Exception
    {
        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторвя");
        Line line3 = new Line(3, "Третья");
        vladivostokskaya = new Station("Владивостокская", line1);
        uralskaya = new Station("Уральская", line1);
        kavkazskaya = new Station("Кавказская", line1);
        pokrovskaya = new Station("Покровская", line2);
        petraVelikogo = new Station("Петра Великого", line2);
        tatarskaya = new Station("Татарская", line2);
        ufimskaya = new Station("Уфимская", line3);
        bashkirskaya = new Station("Башкирская", line3);
        ekaterinburgskaya = new Station("Екатеринбургская", line3);
        line1.addStation(vladivostokskaya);
        line1.addStation(uralskaya);
        line1.addStation(kavkazskaya);
        line2.addStation(pokrovskaya);
        line2.addStation(petraVelikogo);
        line2.addStation(tatarskaya);
        line3.addStation(ufimskaya);
        line3.addStation(bashkirskaya);
        line3.addStation(ekaterinburgskaya);
        stationIndex.addStation(vladivostokskaya);
        stationIndex.addStation(uralskaya);
        stationIndex.addStation(kavkazskaya);
        stationIndex.addStation(pokrovskaya);
        stationIndex.addStation(petraVelikogo);
        stationIndex.addStation(tatarskaya);
        stationIndex.addStation(ufimskaya);
        stationIndex.addStation(bashkirskaya);
        stationIndex.addStation(ekaterinburgskaya);
        routeWithoutConnections = new ArrayList<>();
        routeWithoutConnections.add(vladivostokskaya);
        routeWithoutConnections.add(uralskaya);
        routeWithoutConnections.add(pokrovskaya);
        routeWithoutConnections.add(petraVelikogo);
        List <Station> connection1 = new ArrayList<>();
        connection1.add(uralskaya);
        connection1.add(petraVelikogo);
        stationIndex.addConnection(connection1);
        List <Station> connection2 = new ArrayList<>();
        connection2.add(tatarskaya);
        connection2.add(ufimskaya);
        stationIndex.addConnection(connection2);
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        routeCalculator = new RouteCalculator(stationIndex);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCalculateDuration ()
    {
        double actual = routeCalculator.calculateDuration(routeWithoutConnections);
        double expected = 8.5;
        assertEquals(expected, actual);
    }
    public void testGetShortestRouteOnLine()
    {
        List <Station> actual = routeCalculator.getShortestRoute(vladivostokskaya, kavkazskaya);
        List <Station> expected = new ArrayList<> ();
        expected.add(vladivostokskaya);
        expected.add(uralskaya);
        expected.add(kavkazskaya);
        assertEquals(expected, actual);
    }
}
