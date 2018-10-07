package Lab_2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Logger;

public class CheckingInfo {

    private final HashMap<Double, LinkedList<AreaCheckServlet.RequestInfo>> _resultsByRadius = new HashMap<>();
    private final LinkedList<AreaCheckServlet.RequestInfo> _allResults = new LinkedList<>();

    public CheckingInfo() {
        Logger.getLogger(getClass().getName()).warning("bean created");
    }

    public void registerResult(AreaCheckServlet.RequestInfo info) {
        Double key = Double.valueOf(info.R);
        LinkedList<AreaCheckServlet.RequestInfo> list = _resultsByRadius.get(key);
        if (list == null)
            _resultsByRadius.put(key, list = new LinkedList<>());

        list.add(info);
        _allResults.add(info);
    }

    public LinkedList<AreaCheckServlet.RequestInfo> getResults(double r) {
        return _resultsByRadius.get(Double.valueOf(r));
    }

    public LinkedList<AreaCheckServlet.RequestInfo> getAllResults() {
        return _allResults;
    }
}
