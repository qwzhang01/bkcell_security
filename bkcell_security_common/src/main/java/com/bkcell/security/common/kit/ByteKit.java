package com.bkcell.security.common.kit;

import java.io.*;

public class ByteKit {

    public static byte[] toByte(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return ((String) obj).getBytes();
        }
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    public static <T> T toObject(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        T obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = (T) ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
}
