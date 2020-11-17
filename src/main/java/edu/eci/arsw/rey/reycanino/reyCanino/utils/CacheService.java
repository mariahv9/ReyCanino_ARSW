package edu.eci.arsw.rey.reycanino.reyCanino.utils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;

@Service
public class CacheService {
    private ConcurrentHashMap<String, List<Horario>> cache = new ConcurrentHashMap<String, List<Horario>>();

    public List<Horario> getAll(Horario param) {
        String llave = createKey(param);
        List<Horario> res = null;
        if (cache.containsKey(llave)) {
            res = cache.get(llave);
        }
        return res;
    }

    public void addHorario(List<Horario> param) {
        String llave = createKey((Horario) param.get(0));
        cache.put(llave, param);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                cache.remove(llave);
                timer.cancel();
            }
        };
        timer.schedule(timerTask, 60000, 1);
    }

    private String createKey(Horario horario) {
        Calendar c = Calendar.getInstance();
        String s = "";
        String[] servicios = { "Peluqueria", "Paseo" };
        if (horario.getFechaConsulta() != null) {
            c.setTime(horario.getFechaConsulta());
            s += c.get(Calendar.YEAR);
            s += c.get(Calendar.MONTH) + 1;
            s += c.get(Calendar.DAY_OF_MONTH);
            s += "-" + servicios[Integer.parseInt(horario.getServicio()) - 1];

        } else {
            s += horario.getFi().getYear();
            s += horario.getFi().getMonth().getValue();
            s += horario.getFi().getDayOfMonth();
            s += "-" + horario.getServicio();
        }

        s +="-" + horario.getTiendaCanina();

        return s;
    }
}