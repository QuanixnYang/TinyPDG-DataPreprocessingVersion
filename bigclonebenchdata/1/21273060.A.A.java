public class A{
    public RobotList<Location> sort_decr_Location(RobotList<Location> list, String field) {
        int length = list.size();
        Index_value[] enemy_dist = new Index_value[length];
        if (field.equals("") || field.equals("location")) {
            Location cur_loc = this.getLocation();
            for (int i = 0; i < length; i++) {
                enemy_dist[i] = new Index_value(i, distance(cur_loc, list.get(i)));
            }
        } else if (field.equals("x")) {
            for (int i = 0; i < length; i++) {
                enemy_dist[i] = new Index_value(i, list.get(i).x);
            }
        } else if (field.equals("y")) {
            for (int i = 0; i < length; i++) {
                enemy_dist[i] = new Index_value(i, list.get(i).y);
            }
        } else {
            say("impossible to sort list - nothing modified");
            return list;
        }
        boolean permut;
        do {
            permut = false;
            for (int i = 0; i < length - 1; i++) {
                if (enemy_dist[i].value < enemy_dist[i + 1].value) {
                    Index_value a = enemy_dist[i];
                    enemy_dist[i] = enemy_dist[i + 1];
                    enemy_dist[i + 1] = a;
                    permut = true;
                }
            }
        } while (permut);
        RobotList<Location> new_location_list = new RobotList<Location>(Location.class);
        for (int i = 0; i < length; i++) {
            new_location_list.addLast(list.get(enemy_dist[i].index));
        }
        return new_location_list;
    }
}