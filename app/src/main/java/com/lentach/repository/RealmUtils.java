package com.lentach.repository;

import com.lentach.repository.realmmodel.PostRealmModel;
import com.lentach.api.models.wallpost.Attachment;
import com.lentach.api.models.wallpost.Likes;
import com.lentach.api.models.wallpost.Photo;
import com.lentach.api.models.wallpost.Post;

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
                    post.getAttachments().get(0).getPhoto().getPhoto604(),//TODO Add workaround
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

    public static ArrayList<Post> getAllPostsFromDB(Realm realm){
        RealmResults<PostRealmModel> realmQuery = realm.where(PostRealmModel.class)
                .findAll();
        RealmList<PostRealmModel> artistsList = new RealmList<PostRealmModel>();
        artistsList.addAll(realmQuery.subList(0, realmQuery.size()));
        ArrayList<PostRealmModel> postRealmModelArrayList = new ArrayList<>();
        postRealmModelArrayList.addAll(artistsList);
        ArrayList<Post> postList = new ArrayList<>();

        for (int i = 0; i < postRealmModelArrayList.size(); i++) {

            ArrayList<Attachment> simpleAttachList = new ArrayList<>();
            simpleAttachList.add(new Attachment("photo",new Photo(postRealmModelArrayList.get(i).getPhotoAttach())));

            postList.add(new Post(postRealmModelArrayList.get(i).getId(),
                    postRealmModelArrayList.get(i).getFromId(),
                    postRealmModelArrayList.get(i).getOwnerId(),
                    postRealmModelArrayList.get(i).getPostType(),
                    postRealmModelArrayList.get(i).getDate(),
                    postRealmModelArrayList.get(i).getText(),
                    postRealmModelArrayList.get(i).getIsPinned(),
                    simpleAttachList,
                    new Likes(postRealmModelArrayList.get(i).getLikes()
                    )));

        }

        return postList;
    }
}
