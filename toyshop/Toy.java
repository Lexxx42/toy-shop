package toyshop;


class Toy {
    private final Integer idToy;
    private final Integer dropToy;
    private final String nameToy;
    private final String[] dataToy;

    public Toy(String inputData) {
        String[] data = inputData.split(" ");
        this.idToy = Integer.parseInt(data[0]);
        this.dropToy = Integer.parseInt(data[1]);
        this.nameToy = data[2];
        this.dataToy = data;
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

    public String[] getDataToy() {
        return dataToy;
    }

    @Override
    public String toString() {
        return idToy.toString() + ' ' + dropToy.toString() + ' ' + nameToy;
    }

}
