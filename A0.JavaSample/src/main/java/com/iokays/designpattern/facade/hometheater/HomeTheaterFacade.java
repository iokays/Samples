package com.iokays.designpattern.facade.hometheater;


/**
 * 家庭影院外观
 */
public class HomeTheaterFacade {

    /**
     * 放大器
     */
    private Amplifier amp;

    /**
     * 调谐器
     */
    private Tuner tuner;

    /**
     * DVD播放器
     */
    private DvdPlayer dvd;

    /**
     * CD播放器
     */
    private CdPlayer cd;

    /**
     * 投影仪
     */
    private Projector projector;

    /**
     * 灯光
     */
    private TheaterLights lights;

    /**
     * 屏幕
     */
    private Screen screen;

    /**
     * 爆米花机
     */
    private PopcornPopper popper;

    public HomeTheaterFacade(Amplifier amp, Tuner tuner, DvdPlayer dvd, CdPlayer cd, Projector projector,
                             TheaterLights lights, Screen screen, PopcornPopper popper) {
        this.amp = amp;
        this.tuner = tuner;
        this.dvd = dvd;
        this.cd = cd;
        this.projector = projector;
        this.lights = lights;
        this.screen = screen;
        this.popper = popper;
    }

    /**
     * 观看电影
     */
    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        popper.on();
        popper.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setDvd(dvd);
        amp.setSurroundSound();
        amp.setVolume(5);
        dvd.on();
        dvd.play(movie);
    }

    /**
     * 结束观看电影
     */
    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        dvd.stop();
        dvd.eject();
        dvd.off();
    }

    /**
     * 听CD
     */
    public void listenToCd(String cdTitle) {
        System.out.println("Get ready for an audiopile experence...");
        lights.on();
        amp.on();
        amp.setVolume(5);
        amp.setCd(cd);
        amp.setStereoSound();
        cd.on();
        cd.play(cdTitle);
    }

    /**
     * 结束听CD
     */
    public void endCd() {
        System.out.println("Shutting down CD...");
        amp.off();
        amp.setCd(cd);
        cd.eject();
        cd.off();
    }

    /**
     * 听收音机
     */
    public void listenToRadio(double frequency) {
        System.out.println("Tuning in the airwaves...");
        tuner.on();
        tuner.setFrequency(frequency);
        amp.on();
        amp.setVolume(5);
        amp.setTuner(tuner);
    }

    /**
     * 结束听收音机
     */
    public void endRadio() {
        System.out.println("Shutting down the tuner...");
        tuner.off();
        amp.off();
    }
}
