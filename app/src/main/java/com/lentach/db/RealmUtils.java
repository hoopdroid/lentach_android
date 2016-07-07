package com.lentach.db;

import com.lentach.models.realm.PostRealmModel;
import com.lentach.models.wallpost.Post;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;


/**
 * Contains methods for realm database
 */
public class RealmUtils {

    public static boolean checkIfExists(Realm realm, int id) {

        RealmQuery<PostRealmModel> query = realm.where(PostRealmModel.class)
                .equalTo("id", id);

        return query.count() == 0 ? false : true;
    }

    public static boolean addToFavorites(Realm realm,Post post) {

        if (!RealmUtils.checkIfExists(realm, post.getId())) {
            realm.beginTransaction();
            PostRealmModel postRealmModel = new PostRealmModel(
                    post.getId(),
                    post.getFromId(),
                    post.getOwnerId(),
                    post.getDate(),
                    post.getPostType(),
                    post.getText(),
                    post.getAttachments().get(0).getPhoto().getPhoto604(),
                    post.getIsPinned(),
                    post.getLikes().getCount()
            );

            realm.copyToRealm(postRealmModel);
            realm.commitTransaction();
            return true;
        } else {

            return false;
        }
    }

    public static boolean removeFromFavorites(Realm realm,Post post) {

            PostRealmModel postRealmModel = new PostRealmModel(
                    post.getId(),
                    post.getFromId(),
                    post.getOwnerId(),
                    post.getDate(),
                    post.getPostType(),
                    post.getText(),
                    post.getAttachments().get(0).getPhoto().getPhoto604(),
                    post.getIsPinned(),
                    post.getLikes().getCount()
            );
            realm.beginTransaction();
            RealmQuery rows = realm.where(PostRealmModel.class).equalTo("id", postRealmModel.getId());
            rows.findAll().clear();
            realm.commitTransaction();
            return true;



    }

    public static ArrayList getAllPostsFromDB(Realm realm){
        RealmResults<PostRealmModel> realmQuery = realm.where(PostRealmModel.class)
                .findAll();
        RealmList<PostRealmModel> artistsList = new RealmList<PostRealmModel>();
        artistsList.addAll(realmQuery.subList(0, realmQuery.size()));
        ArrayList<PostRealmModel> arrayList = new ArrayList<>();
        arrayList.addAll(artistsList);
        int a = 5;
        return  arrayList;
    }
}
