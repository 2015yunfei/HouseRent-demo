package service;

import domain.House;

/**
 * HouseService.java<=>类 [业务层]
 * 定义House[] ,保存House对象
 * 1. 响应HouseView的调用
 * 2. 完成对房屋信息的各种操作(增删改查c[create]r[read]u[update]d[delete])
 */
public class HouseService {

    private House[] houses; //保存House对象
    private int houseNums = 1; //记录当前有多少个房屋信息
    private int idCounter = 1; //记录当前的id增长到哪个值


    public HouseService(int size) {
        houses = new House[size];//当创建HouseService对象，指定数组大小
        //为了配合测试列表信息，这里初始化一个House对象
        houses[0] = new House(1, "jack", "112", "海淀区", 2000, "未出租");
    }

    //findById方法,返回House对象或者null
    public House findById(int findId) {
        for (int i = 0; i < houseNums; i++) {
            if (findId == houses[i].getId()) {
                return houses[i];
            }
        }
        return null;
    }


    public boolean del(int delId) {
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (delId == houses[i].getId()) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return false;
        }

        for (int i = index; i < houseNums - 1; i++) {
            houses[i] = houses[i + 1];
        }

        //把当有存在的房屋信息的最后一个设置null
        houses[--houseNums] = null;
        return true;
    }

    public boolean add(House newHouse) {
        if (houseNums == houses.length) {
            resizeArray();
        }

        //把newHouse对象加入到，新增加了一个房屋
        houses[houseNums++] = newHouse;
        //id自增长的机制
        newHouse.setId(++idCounter);
        return true;
    }

    private void resizeArray() {
        // 新容量是旧容量的两倍
        int newCapacity = houses.length * 2;
        House[] newHouses = new House[newCapacity];

        // 将旧数组中的元素复制到新数组
        if (houseNums >= 0) System.arraycopy(houses, 0, newHouses, 0, houseNums);

        houses = newHouses;
    }

    //返回houses
    public House[] list() {
        return houses;
    }
}
