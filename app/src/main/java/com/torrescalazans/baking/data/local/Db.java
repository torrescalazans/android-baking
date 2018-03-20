package com.torrescalazans.baking.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.torrescalazans.baking.data.model.Name;
import com.torrescalazans.baking.data.model.Profile;
import com.torrescalazans.baking.data.model.Recipe;

import java.util.Date;

public class Db {

    public Db() { }

    public abstract static class RibotProfileTable {
        public static final String TABLE_NAME = "ribot_profile";

        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_HEX_COLOR = "hex_color";
        public static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
        public static final String COLUMN_AVATAR = "avatar";
        public static final String COLUMN_BIO = "bio";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
                        COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                        COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                        COLUMN_HEX_COLOR + " TEXT NOT NULL, " +
                        COLUMN_DATE_OF_BIRTH + " INTEGER NOT NULL, " +
                        COLUMN_AVATAR + " TEXT, " +
                        COLUMN_BIO + " TEXT" +
                " ); ";

        public static ContentValues toContentValues(Profile profile) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_EMAIL, profile.email());
            values.put(COLUMN_FIRST_NAME, profile.name().first());
            values.put(COLUMN_LAST_NAME, profile.name().last());
            values.put(COLUMN_HEX_COLOR, profile.hexColor());
            values.put(COLUMN_DATE_OF_BIRTH, profile.dateOfBirth().getTime());
            values.put(COLUMN_AVATAR, profile.avatar());
            if (profile.bio() != null) values.put(COLUMN_BIO, profile.bio());
            return values;
        }

        public static Profile parseCursor(Cursor cursor) {
            Name name = Name.create(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME)));
            long dobTime = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE_OF_BIRTH));

            return Profile.builder()
                    .setName(name)
                    .setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)))
                    .setHexColor(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HEX_COLOR)))
                    .setDateOfBirth(new Date(dobTime))
                    .setAvatar(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVATAR)))
                    .setBio(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BIO)))
                    .build();
        }
    }

    public abstract static class RecipeTable {
        public static final String TABLE_NAME = "recipe";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_INGREDIENTS = "ingredients";
        public static final String COLUMN_STEPS = "steps";
        public static final String COLUMN_SERVING = "serving";
        public static final String COLUMN_IMAGE_URL = "image_url";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " TEXT PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_INGREDIENTS + " TEXT, " +
                        COLUMN_STEPS + " TEXT, " +
                        COLUMN_SERVING + " INTEGER NOT NULL, " +
                        COLUMN_IMAGE_URL + " TEXT" +
                        " ); ";

        public static ContentValues toContentValues(Recipe recipe) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, recipe.id());
            values.put(COLUMN_NAME, recipe.name());

            //values.put(COLUMN_INGREDIENTS, recipe.ingredients()); // TODO
            //values.put(COLUMN_STEPS, recipe.steps());

            values.put(COLUMN_SERVING, recipe.servings());
            values.put(COLUMN_IMAGE_URL, recipe.imageUrl());
            return values;
        }

        public static Recipe parseCursor(Cursor cursor) {
            return Recipe.builder()
                    .setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)))
                    .setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)))

                    //.setIngredients(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INGREDIENTS))) // TODO
                    //.setSteps(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STEPS)))

                    .setServings(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SERVING)))
                    .setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_URL)))
                    .build();
        }
    }
}
