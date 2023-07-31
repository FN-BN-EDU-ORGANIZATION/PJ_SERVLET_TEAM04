package Domain.Common.Dto;

public class FcstDtoImpl implements FcstDto {

    private String nx;
    private String ny;
    private String fcstDate;
    private String fcstTime;
    private String fcstPTY;
    private String fcstRN1;
    private String fcstREH;
    private String fcstT1H;

    public FcstDtoImpl() {
    }

    public FcstDtoImpl(String nx, String ny, String fcstDate, String fcstTime, String fcstPTY, String fcstRN1, String fcstREH, String fcstT1H) {
        this.nx = nx;
        this.ny = ny;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.fcstPTY = fcstPTY;
        this.fcstRN1 = fcstRN1;
        this.fcstREH = fcstREH;
        this.fcstT1H = fcstT1H;
    }

    @Override
	public String getNx() {
        return nx;
    }

    @Override
	public void setNx(String nx) {
        this.nx = nx;
    }

    @Override
	public String getNy() {
        return ny;
    }

    @Override
	public void setNy(String ny) {
        this.ny = ny;
    }

    @Override
	public String getFcstDate() {
        return fcstDate;
    }

    @Override
	public void setFcstDate(String fcstDate) {
        this.fcstDate = fcstDate;
    }

    @Override
	public String getFcstTime() {
        return fcstTime;
    }

    @Override
	public void setFcstTime(String fcstTime) {
        this.fcstTime = fcstTime;
    }

    @Override
	public String getFcstPTY() {
        return fcstPTY;
    }

    @Override
	public void setFcstPTY(String fcstPTY) {
        this.fcstPTY = fcstPTY;
    }

    @Override
	public String getFcstRN1() {
        return fcstRN1;
    }

    @Override
	public void setFcstRN1(String fcstRN1) {
        this.fcstRN1 = fcstRN1;
    }

    @Override
	public String getFcstREH() {
        return fcstREH;
    }

    @Override
	public void setFcstREH(String fcstREH) {
        this.fcstREH = fcstREH;
    }

    @Override
	public String getFcstT1H() {
        return fcstT1H;
    }

    @Override
	public void setFcstT1H(String fcstT1H) {
        this.fcstT1H = fcstT1H;
    }
}
