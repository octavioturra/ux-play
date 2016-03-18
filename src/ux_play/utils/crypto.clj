; Just use java libs. It's exactly the same!
(ns ux-play.utils.crypto
  (:import
    java.security.SecureRandom
    javax.crypto.SecretKeyFactory
    javax.crypto.spec.PBEKeySpec))

(defn pbkdf2
  ; Get a hash for the given string and optional salt
  ([x salt]
   (let [k (PBEKeySpec. (.toCharArray x) (.getBytes salt) 1000 192)
         f (SecretKeyFactory/getInstance "PBKDF2WithHmacSHA1")]
     (format "%x"
             (java.math.BigInteger. (.getEncoded (.generateSecret f k)))))))
