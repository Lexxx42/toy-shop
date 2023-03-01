package toyshop;

class Toy {
    private final Integer idToy;
    private final Integer dropToy;
    private final String nameToy;
    private String inputData;

    public Toy(String inputData) {
        String[] data = inputData.split(" ");
        this.idToy = Integer.parseInt(data[0]);
        this.dropToy = Integer.parseInt(data[1]);
        this.nameToy = data[2];
    }

    public Integer getIdToy() {
        return idToy;
    }

    public Integer getDropToy() {
        return dropToy;
    }

    public String getNameToy() {
        return nameToy;
    }

}
