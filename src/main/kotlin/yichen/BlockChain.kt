// package yichen
//
//import (
//	"time"
//	"context"
//	"crypto/sha256"
//	"fmt"
//	"strconv"
//)
//
//type Address []byte
//type UUID string
//
//type Tx struct {
//	Addr      Address
//	Nonce     uint64
//	Recipient Address
//	Amount    uint64
//	Signature []byte
//}
//
//func makeUnsignedTx(from, to Address, amount, nonce uint64) *Tx {
//	return &Tx{
//		Addr:      from,
//		Nonce:     nonce,
//		Recipient: to,
//		Amount:    amount,
//	}
//}
//
//func (t *Tx) Hash() []byte {
//	s := sha256.New()
//	s.Write(t.Addr)
//	s.Write([]byte(strconv.FormatUint(t.Nonce, 10)))
//	s.Write(t.Recipient)
//	s.Write([]byte(strconv.FormatUint(t.Amount, 10)))
//	return s.Sum(nil)
//}
//
//func (t *Tx) Sign(signingKey []byte) {
//	hash := t.Hash()
//	// not really signing anything, just to demonstrate the process of
//	// signing a transaction: apply private key on the Hash() of tx.
//	t.Signature = []byte(fmt.Sprintf("signing hash %s with private key %s", hash, signingKey))
//}
//
//type TxResult struct {
//	Tx *Tx
//	// 0 = ok, other = reverted.
//	ExecCode    int
//	BlockHeight int
//}
//
//type Response struct {
//	// when code is 0, RPC succeeded, otherwise something wrong happened.
//	Code int
//}
//
//type BlockChain interface {
//	/// @broadcast the @p tx to network. An error will be returned if the TX is immediately rejected.
//	Broadcast(ctx context.Context, tx *Tx) (*Response, error)
//	/// @returns Tx of @p hash if it has been confirmed (packed in a block).
//	/// Otherwise, an error will be returned.
//	GetTx(ctx context.Context, hash []byte) (*TxResult, error)
//	/// @returns the nonce of account @p address.
//	GetNonce(ctx context.Context, address Address) (uint64, error)
//}
//
////////////////////////////////////////////////////////////////////////////////
//
//type TransferETHRequest interface {
//	GetTo() Address
//	GetAmount() int
//	GetUUID() UUID
//}
//
//type Storage interface {
//	// @returns error if the key was not set.
//	Set(ctx context.Context, key string, value []byte) error
//	// @returns true if the key was set, false if it was not set.
//	SetNX(ctx context.Context, key string, value []byte) (bool, error)
//	// @returns error if the key does not exist.
//	Get(ctx context.Context, key string) ([]byte, error)
//	// add more if you need
//}
//
//type moneySender struct {
//	Addr    Address
//	PrivKey []byte
//	Blk     BlockChain
//	Storage Storage
//}
//
//func (m moneySender) Send(ctx context.Context, req TransferETHRequest) error {
//	// TODO: implement here.
//	// The following code has many problems:
//	// 1. It does not satisfy the requirement of "once and only once".
//	// 2. It waits too long, which exceeds the requirement of response time of 10ms.
//	nonce, err := m.Blk.GetNonce(ctx, m.Addr)
//	if err != nil {
//		return err
//	}
//	tx := makeUnsignedTx(m.Addr, req.GetTo(), uint64(req.GetAmount()), nonce)
//	tx.Sign(m.PrivKey)
//	res, err := m.Blk.Broadcast(ctx, tx)
//	if err != nil {
//		// Can you really return an error here if you want to
//		// satisfy the requirement of "once and only once"?
//		return err
//	}
//	if res.Code != 0 {
//		return fmt.Errorf("failed to broadcast tx: %v", res)
//	}
//	// store broadcasted tx
//	err = m.Storage.Set(ctx, string(req.GetUUID()), tx.Hash())
//	if err != nil {
//		// can you?
//		return err
//	}
//	for {
//		// check if tx is confirmed
//		_, err := m.Blk.GetTx(ctx, tx.Hash())
//		if err != nil {
//			time.Sleep(time.Second)
//			continue
//		} else {
//			break
//		}
//	}
//	return nil
//}
//
//func (m moneySender) GetDetail(ctx context.Context, id UUID) (*TxResult, error) {
//	// TODO: implement here.
//	// check if tx is confirmed
//	txHash, err := m.Storage.Get(ctx, string(id))
//	if err != nil {
//		return nil, err
//	}
//	// check if tx is confirmed
//	_, err = m.Blk.GetTx(ctx, txHash)
//	if err != nil {
//		return nil, err
//	}
//	return nil, nil
//}
//
///// Problem statement:
///// You will need to write a service that implements the User interface:
/////
///// (1) Send method can take transfer request and it promises to
/////     send the *Amount* of ETH to address *To*, and no matter what happened,
/////     it will do this job	once and only once, eventually.
///// (2) Query tx status by uuid.
/////

package yichen

import java.security.MessageDigest
import java.util.concurrent.CompletableFuture

typealias Address = ByteArray
typealias UUID = String

data class Tx(
    val addr: Address,
    val nonce: Long,
    val recipient: Address,
    val amount: Long,
    var signature: ByteArray? = null
) {
    fun hash(): ByteArray {
        val sha = MessageDigest.getInstance("SHA-256")
        sha.update(addr)
        sha.update(nonce.toString().toByteArray())
        sha.update(recipient)
        sha.update(amount.toString().toByteArray())
        return sha.digest()
    }

    fun sign(signingKey: ByteArray) {
        val h = hash()
        // 模拟签名
        signature = "signing hash ${h.contentToString()} with private key ${signingKey.contentToString()}"
            .toByteArray()
    }
}

data class TxResult(
    val tx: Tx,
    val execCode: Int,
    val blockHeight: Int
)

data class Response(
    val code: Int
)

interface BlockChain {
    fun broadcast(tx: Tx): Response
    fun getTx(hash: ByteArray): TxResult

    //
    fun getNonce(address: Address): Long
}

interface TransferETHRequest {
    fun getTo(): Address
    fun getAmount(): Int
    fun getUUID(): UUID
}

interface Storage {
    fun set(key: String, value: ByteArray): Boolean
    fun setNX(key: String, value: ByteArray): Boolean
    fun get(key: String): ByteArray?
}

class MoneySender(
    private val addr: Address,
    private val privKey: ByteArray,
    private val blk: BlockChain,
    private val storage: Storage
) {

    fun send(req: TransferETHRequest): CompletableFuture<Void> {
        return CompletableFuture.runAsync {
            val nonce = blk.getNonce(addr)
            val tx = Tx(addr, nonce, req.getTo(), req.getAmount().toLong())
            tx.sign(privKey)

            // 保证“once and only once”
            if (!storage.setNX(req.getUUID(), tx.hash())) {
                // 已存在，不重复发送
                return@runAsync
            }

            val res = blk.broadcast(tx)
            if (res.code != 0) {
                throw RuntimeException("failed to broadcast tx uuid: ${req.getUUID()}  code: ${res.code}")
            }

            // 等待确认（这里应该异步或回调，避免阻塞 >10ms）
            while (true) {
                val txResult = getDetail(req.getUUID())
                if (txResult == null) {
                    Thread.sleep(1000)
                    continue
                } else {
                    if (txResult.execCode == 0) {
                        println("tx ${req.getUUID()} exec success")
                    } else {
                        throw RuntimeException("tx ${req.getUUID()} exec error")
                    }
                }
            }
        }
    }

    fun getDetail(id: UUID): TxResult? {
        val txHash = storage.get(id) ?: throw RuntimeException("txHash not found by uuid: ${id}")
        return try {
            blk.getTx(txHash)
        } catch (_: Exception) {
            null
        }
    }
}
